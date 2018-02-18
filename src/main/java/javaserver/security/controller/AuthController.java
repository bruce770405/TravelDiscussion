package javaserver.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javaserver.model.Login;
import javaserver.security.JwtAuthenticationRequest;
import javaserver.security.JwtAuthenticationResponse;

@RestController
public class AuthController {
	// @Value("${jwt.header}")
	private String tokenHeader = "Authorization";

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) throws AuthenticationException {
		final String token = authService.login(authRequest.getUsername(),authRequest.getPassword());
		// Return the token
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
		String token = request.getHeader(tokenHeader);
		String refreshedToken = authService.refresh(token);
		if (refreshedToken == null) {
			return ResponseEntity.badRequest().body(null);
		} else {
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		}
	}

	@RequestMapping(value = "/auth/register", method = RequestMethod.POST)
	public Login register(@RequestBody Login addedUser) throws AuthenticationException {
		return authService.register(addedUser);
	}
}
