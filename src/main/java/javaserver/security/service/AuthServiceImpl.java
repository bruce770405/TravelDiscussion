package javaserver.security.service;

import javaserver.entity.UserEntity;
import javaserver.error.RestfulException;
import javaserver.error.errorcode.AuthErrorCode;
import javaserver.repository.UserRepository;
import javaserver.security.AuthUser;
import javaserver.security.UserRegisterRequest;
import javaserver.security.dto.JwtAuthenticationResponse;
import javaserver.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
    public ResponseEntity<?> register(UserRegisterRequest registerRequest) {
        final String username = registerRequest.getUsername();
        final String nickname = registerRequest.getNickname();
        // 先檢查nickname,
        Optional<UserEntity> optional = userRepository.findByUsernameOrNickName(username, nickname);
        if (!optional.isPresent()) {
            throw new RestfulException(AuthErrorCode.ACCOUNT_HAS_EXISTED);
        }

        UserEntity returnLogin = userRepository.save(prepareRegisterUser(registerRequest));
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
    public JwtAuthenticationResponse refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        final String username = jwtUtil.getUsernameFromToken(token);
        AuthUser user = (AuthUser) userDetailsService.loadUserByUsername(username);

        if (!jwtUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            throw new AuthenticationServiceException("token is incorrect");
        }

        return new JwtAuthenticationResponse(jwtUtil.refreshToken(token));
    }

    private UserEntity prepareRegisterUser(UserRegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();
        final String rawPassword = registerRequest.getPassword();
        userEntity.setPassword(encoder.encode(rawPassword));
        userEntity.setLastPasswordResetDate(new Date());
        userEntity.setRoles("ROLE_USER");
        userEntity.setStopTag((short) 0);
        userEntity.setLevelId(1);
        userEntity.setNickname(registerRequest.getNickname());
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setMail(registerRequest.getMail());
        if (registerRequest.getIcon() != null) {
            userEntity.setIcon(registerRequest.getIcon().getBody());
            userEntity.setFileName(registerRequest.getIcon().getFileName());
        }
        return userEntity;
    }

}
