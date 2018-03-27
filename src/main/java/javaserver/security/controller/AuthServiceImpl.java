package javaserver.security.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//	AuthenticationManager 是Spring Secrurity提供的，它根據我们實現的UserDetailServiceImpl去進行用戶驗證
	private AuthenticationManager authenticationManager;
	private UserDetailsService userDetailsService;
	private JwtUtil util;
	private UserJpaRepository userRepository;
	//密碼加密工具
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtUtil util, UserJpaRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.util = util;
		this.userRepository = userRepository;
	}


	@Override
	public ResponseEntity<?> register(Login userToAdd) throws IOException {
		 final String username = userToAdd.getUsername();
		 final String nickname = userToAdd.getNickname();
		 //先檢查nickname,
		 Login login = userRepository.findByUsername(username);
	        if(login != null) 
	            return new ResponseEntity<>( new ResultStatusModel(false,"此帳號已經註冊過",null),HttpStatus.BAD_REQUEST);
	      login = userRepository.findByNickname(nickname);
	      if(login != null) 
	            return new ResponseEntity<>(new ResultStatusModel(false,"已有相同的暱稱",null),HttpStatus.BAD_REQUEST);
			
	        userToAdd = AddUserHandler(userToAdd);
	        Login returnLogin = userRepository.save(userToAdd);
	        return new ResponseEntity<>(new ResultStatusModel(true,"註冊成功",returnLogin),HttpStatus.OK);
	    }

	@Override
	public ResponseEntity<?> login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		// Perform the security
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = util.generateToken(userDetails);
		Login login = userRepository.findByUsername(username);
		login.setPassword("");
		ResultStatusModel model = new ResultStatusModel(true,"認證成功，回傳token",new LoginUserModel( login ,new JwtAuthenticationResponse(token)));
		return ResponseEntity.ok(model);
	}

	@Override
	public ResponseEntity<?> refresh(String oldToken) {
		  final String token = oldToken.substring(tokenHead.length());
	        String username = util.getUsernameFromToken(token);
	        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);
	        if (util.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
	            return new ResponseEntity<>(util.refreshToken(token),HttpStatus.OK);
	        else
	            return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	    }

	
	private Login AddUserHandler(Login userToAdd) {
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
        return userToAdd;
	}
	
}
