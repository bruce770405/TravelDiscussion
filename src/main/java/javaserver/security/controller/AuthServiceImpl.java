package javaserver.security.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javaserver.model.Login;
import javaserver.model.combine.LoginUserModel;
import javaserver.model.combine.ResultStatusModel;
import javaserver.repository.UserJpaRepository;
import javaserver.security.AuthUser;
import javaserver.security.JwtAuthenticationResponse;
import javaserver.util.JwtUtil;
import javaserver.util.Util;



@Service
public class AuthServiceImpl implements AuthService {

	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private JwtUtil util;
	private UserJpaRepository userRepository;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtUtil util, UserJpaRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.util = util;
		this.userRepository = userRepository;
	}

//	@Value("${jwt.tokenHead}")
	private String tokenHead = "auth ";

	@Override
	public ResultStatusModel register(Login userToAdd) throws IOException {
		 final String username = userToAdd.getUsername();
		 final String nickname = userToAdd.getNickname();
		 //先檢查nickname,
		 Login login = userRepository.findByUsername(username);
	        if(login != null) 
	            return  new ResultStatusModel(false,"此帳號已經註冊過",null);
	      login = userRepository.findByNickname(nickname);
	      if(login != null) 
	            return new ResultStatusModel(false,"已有相同的暱稱",null);
			
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        final String rawPassword = userToAdd.getPassword();
	        userToAdd.setPassword(encoder.encode(rawPassword));
	        userToAdd.setLastPasswordResetDate(new Date());
	        userToAdd.setRoles("ROLE_USER");
	        userToAdd.setStopTag("");
	        userToAdd.setLevelId(1);
	        if(userToAdd.getIcon() == null) 
                 userToAdd.setIcon("");
	        else
	        	Util.base64ToImageFile(userToAdd.getUsername(),userToAdd.getIcon());
	        Login returnLogin = userRepository.save(userToAdd);
	        
	        return new ResultStatusModel(true,"註冊成功",returnLogin);
	    }

//	AuthenticationManager 是Spring Secrurity提供的，它根據我们實現的UserDetailServiceImpl去進行用戶驗證
	@Override
	public ResultStatusModel login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		// Perform the security
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = util.generateToken(userDetails);
		Login login = userRepository.findByUsername(username);
		login.setPassword("");
		return new ResultStatusModel(true,"認證成功，回傳token",new LoginUserModel( login ,new JwtAuthenticationResponse(token)));
	}

	@Override
	public String refresh(String oldToken) {
		  final String token = oldToken.substring(tokenHead.length());
	        String username = util.getUsernameFromToken(token);
	        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);
	        if (util.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
	            return util.refreshToken(token);
	        else
	            return null;
	    }

}
