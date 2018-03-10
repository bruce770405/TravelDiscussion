package javaserver.security.controller;

import java.io.IOException;

import javaserver.model.Login;
import javaserver.model.combine.ResultStatusModel;

public interface AuthService {
	ResultStatusModel register(Login userToAdd) throws IOException;
	ResultStatusModel login(String username, String password);
    String refresh(String oldToken);
}