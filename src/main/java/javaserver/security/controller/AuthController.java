package javaserver.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javaserver.entity.LoginData;
import javaserver.security.JwtAuthenticationRequest;

@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthController {
	
	 @Value("${jwt.header}")
	private String tokenHeader;

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
		return authService.login(authRequest.getUsername(),authRequest.getPassword());
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
		String token = request.getHeader(tokenHeader);
		return authService.refresh(token);
	}

	
	/**
	 * 註冊
	 * @param addedUser
	 * @return
	 * @throws AuthenticationException
	 * @throws IOException 
	 */
	@RequestMapping(value = "auth/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginData addedUser) throws AuthenticationException, IOException {
		return authService.register(addedUser);
	}
	
	
	

}
