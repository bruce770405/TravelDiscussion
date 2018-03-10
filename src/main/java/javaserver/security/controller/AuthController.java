package javaserver.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javaserver.model.Login;
import javaserver.model.combine.ResultStatusModel;
import javaserver.security.JwtAuthenticationRequest;
import javaserver.security.JwtAuthenticationResponse;

@RestController
public class AuthController {
	// @Value("${jwt.header}")
	private String tokenHeader = "Authorization";

	@Autowired
	private AuthService authService;

	/**
	 * 登入驗證
	 * @param authRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) throws AuthenticationException {
		return ResponseEntity.ok(authService.login(authRequest.getUsername(),authRequest.getPassword()));
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
		String token = request.getHeader(tokenHeader);
		String refreshedToken = authService.refresh(token);
		if (refreshedToken == null) {
			return ResponseEntity.badRequest().body(null);
		} else {
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		}
	}

	
	/**
	 * 註冊
	 * @param addedUser
	 * @return
	 * @throws AuthenticationException
	 * @throws IOException 
	 */
	@RequestMapping(value = "auth/register", method = RequestMethod.POST)
	public ResultStatusModel register(@RequestBody Login addedUser) throws AuthenticationException, IOException {
		
		return authService.register(addedUser);
	}
	
	
	

}
