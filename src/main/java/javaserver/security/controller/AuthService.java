package javaserver.security.controller;

import javaserver.entity.LoginEntity;
import javaserver.security.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<?> register(LoginEntity userToAdd) throws IOException;

    JwtAuthenticationResponse login(String username, String password);

    ResponseEntity<?> refresh(String oldToken);
}