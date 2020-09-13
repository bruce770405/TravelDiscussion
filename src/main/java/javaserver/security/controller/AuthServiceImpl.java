package javaserver.security.controller;

import javaserver.entity.LoginEntity;
import javaserver.error.RestfulException;
import javaserver.error.errorcode.AuthErrorCode;
import javaserver.repository.UserJpaRepository;
import javaserver.security.AuthUser;
import javaserver.security.JwtAuthenticationResponse;
import javaserver.util.JwtUtil;
import javaserver.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    /**
     * AuthenticationManager 是Spring
     * Security提供的，它根據我们實現的UserDetailServiceImpl去進行用戶驗證
     */
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil util;
    private final UserJpaRepository userRepository;
    // 密碼加密工具
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtUtil util, UserJpaRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.util = util;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> register(LoginEntity userToAdd) {
        final String username = userToAdd.getUsername();
        final String nickname = userToAdd.getNickname();
        // 先檢查nickname,
        LoginEntity login = userRepository.findByUsernameOrNickName(username, nickname);
        if (!Objects.isNull(login)) {
            throw new RestfulException(AuthErrorCode.ACCOUNT_HAS_EXISTED);
        }

        userToAdd = AddUserHandler(userToAdd);
        LoginEntity returnLogin = userRepository.save(userToAdd);
        returnLogin.setPassword(null);
        return ResponseEntity.ok(returnLogin);
    }

    @Override
    public JwtAuthenticationResponse login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = util.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public ResponseEntity<?> refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = util.getUsernameFromToken(token);
        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);
        if (util.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
            return new ResponseEntity<>(util.refreshToken(token), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private LoginEntity AddUserHandler(LoginEntity userToAdd) {
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRoles("ROLE_USER");
        userToAdd.setStopTag((short) 0);
        userToAdd.setLevelId(1);
        if (userToAdd.getIcon() == null)
            userToAdd.setIcon("");
        else
            Util.base64ToImageFile(userToAdd.getUsername(), userToAdd.getIcon());
        return userToAdd;
    }

}
