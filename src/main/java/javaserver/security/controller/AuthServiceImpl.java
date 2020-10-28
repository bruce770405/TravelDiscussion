package javaserver.security.controller;

import javaserver.entity.LoginEntity;
import javaserver.error.RestfulException;
import javaserver.error.errorcode.AuthErrorCode;
import javaserver.repository.UserRepository;
import javaserver.security.AuthUser;
import javaserver.security.JwtAuthenticationResponse;
import javaserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    // 密碼加密工具
    private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtUtil util, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = util;
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

        userToAdd = prepareRegisterUser(userToAdd);
        LoginEntity returnLogin = userRepository.save(userToAdd);
        returnLogin.setPassword(null);
        return ResponseEntity.ok(returnLogin);
    }

    @Override
    public JwtAuthenticationResponse login(String username, String password) {
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtUtil.generateToken(userDetails);
        return new JwtAuthenticationResponse(token);
    }

    @Override
    public ResponseEntity<?> refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        final String username = jwtUtil.getUsernameFromToken(token);
        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);

        if (!jwtUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(jwtUtil.refreshToken(token));
    }

    private LoginEntity prepareRegisterUser(LoginEntity userToAdd) {
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRoles("ROLE_USER");
        userToAdd.setStopTag((short) 0);
        userToAdd.setLevelId(1);
        if (!StringUtils.isEmpty(userToAdd.getIcon())) {
//            jwtUtil.base64ToImageFile(userToAdd.getUsername(), userToAdd.getIcon());
        }
        return userToAdd;
    }

}
