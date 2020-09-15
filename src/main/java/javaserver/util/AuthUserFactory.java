package javaserver.util;

import javaserver.entity.LoginEntity;
import javaserver.security.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public abstract class AuthUserFactory {

    public static AuthUser create(LoginEntity l) {
        return new AuthUser(l.getId(),
                l.getNickname(),
                l.getUsername(),
                l.getPassword(),
                l.getGender(), l.getMail(), mapToGrantedAuthorities(l.getRoles()),
                l.getLastPasswordResetDate(), l.getStopTag());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String authorities) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(authorities));
        return list;
//		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
