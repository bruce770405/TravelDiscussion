package javaserver.security.controller;

import javaserver.model.Login;

public interface AuthService {
    Login register(Login userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}