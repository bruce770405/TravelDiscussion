package javaserver.model.combine;

import javaserver.entity.LoginData;
import javaserver.security.JwtAuthenticationResponse;

public class LoginUserModel {

	public LoginUserModel(LoginData login, JwtAuthenticationResponse jwt) {
		this.login = login;
		this.token = jwt.getToken();
	}

	public LoginData getLogin() {
		return login;
	}

	public void setLogin(LoginData login) {
		this.login = login;
	}



	public String getToken() {
		return token;
	}
	
	

	/**
	 * 
	 */
//	private static final long serialVersionUID = 8771816812204012352L;
	private LoginData login;
	private final String token;
}
