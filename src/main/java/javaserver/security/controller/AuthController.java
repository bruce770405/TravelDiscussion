package javaserver.security.controller;

import javaserver.security.JwtAuthenticationRequest;
import javaserver.security.JwtAuthenticationResponse;
import javaserver.security.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 登入驗證
     */
    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authRequest) throws AuthenticationException {
        JwtAuthenticationResponse response = authService.login(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    /**
     * 刷新
     */
    @GetMapping(value = "/refresh")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        return authService.refresh(request.getHeader(tokenHeader));
    }


    /**
     * 註冊
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) throws AuthenticationException, IOException {
        return authService.register(request.convertToEntity());
    }


}
