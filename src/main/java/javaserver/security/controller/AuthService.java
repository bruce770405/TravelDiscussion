package javaserver.security.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import javaserver.model.Login;

public interface AuthService {
	ResponseEntity<?> register(Login userToAdd) throws IOException;
	ResponseEntity<?> login(String username, String password);
	ResponseEntity<?> refresh(String oldToken);
}