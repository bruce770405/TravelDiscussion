package javaserver.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javaserver.entity.LoginData;
import javaserver.security.AuthUser;

public class AuthUserFactory {
	private AuthUserFactory() {

	}
//	Long id, String username, String password,String gender, String mail,int levelId,
//	Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate,String stopTag) {
//this.id = id
	public static AuthUser create(LoginData l) {
		return new AuthUser(l.getId(),
				            l.getNickname(),
				            l.getUsername(),
				            l.getPassword(),
				            l.getGender(),l.getMail(),
				            l.getLevelId(),
				            mapToGrantedAuthorities(l.getRoles()), 
				            l.getLastPasswordResetDate(),l.getStopTag());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(String authorities) {
		List<GrantedAuthority>list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority(authorities));
		return list;
//		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
