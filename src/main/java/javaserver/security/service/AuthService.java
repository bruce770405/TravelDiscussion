package javaserver.security.service;

import javaserver.security.UserRegisterRequest;
import javaserver.security.dto.JwtAuthenticationResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<?> register(UserRegisterRequest userToAdd) throws IOException;

    JwtAuthenticationResponse login(String username, String password);

    JwtAuthenticationResponse refresh(String oldToken);
}