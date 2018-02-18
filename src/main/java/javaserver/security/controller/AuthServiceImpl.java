package javaserver.security.controller;

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
import javaserver.repository.UserJpaRepository;
import javaserver.security.AuthUser;
import javaserver.util.JwtUtil;



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
	public Login register(Login userToAdd) {
		 final String username = userToAdd.getUsername();
	        if(userRepository.findByUsername(username)!=null) {
	            return null;
	        }
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        final String rawPassword = userToAdd.getPassword();
	        userToAdd.setPassword(encoder.encode(rawPassword));
	        userToAdd.setLastPasswordResetDate(new Date());
	        userToAdd.setRoles("ROLE_USER");
	        return userRepository.save(userToAdd);
	    }

	@Override
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		// Perform the security
		
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String token = util.generateToken(userDetails);
		return token;
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
