package javaserver.util;

import javaserver.entity.UserEntity;
import javaserver.security.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AuthUserFactory {

    public static AuthUser create(UserEntity l) {
        return new AuthUser(l.getId(),
                l.getNickname(),
                l.getUsername(),
                l.getPassword(),
                l.getGender(), l.getMail(), mapToGrantedAuthorities(l.getRoles()),
                l.getLastPasswordResetDate(), l.getStopTag());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String authorities) {
        return Stream.of(authorities).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
