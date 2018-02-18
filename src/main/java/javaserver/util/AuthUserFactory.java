package javaserver.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javaserver.model.Login;
import javaserver.security.AuthUser;

public class AuthUserFactory {
	private AuthUserFactory() {

	}

	public static AuthUser create(Login l) {
		return new AuthUser(l.getId(), l.getUsername(), l.getPassword(), l.getMail(),
				mapToGrantedAuthorities(l.getRoles()), l.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(String authorities) {
		List<GrantedAuthority>list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(authorities));
		return list;
//		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
