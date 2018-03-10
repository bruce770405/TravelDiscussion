package javaserver.model.combine;

import javaserver.model.Login;
import javaserver.security.JwtAuthenticationResponse;

public class LoginUserModel {

	public LoginUserModel(Login login, JwtAuthenticationResponse jwt) {
		this.login = login;
		this.jwt = jwt;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public JwtAuthenticationResponse getJwt() {
		return jwt;
	}

	public void setJwt(JwtAuthenticationResponse jwt) {
		this.jwt = jwt;
	}

	/**
	 * 
	 */
//	private static final long serialVersionUID = 8771816812204012352L;
	private Login login;
	private JwtAuthenticationResponse jwt;
}
