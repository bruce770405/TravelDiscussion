package javaserver.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthUser implements UserDetails {
	private static final long serialVersionUID = -3723935457166134434L;
	private final Long id;
	private final String username;
	private final String password;
	private final String mail;
	private final Collection<? extends GrantedAuthority> authorities;
	private final Date lastPasswordResetDate;

	public AuthUser(Long id, String username, String password, String mail,
			Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.authorities = authorities;
		this.lastPasswordResetDate = lastPasswordResetDate;
		
	}

	// 返回分配给用户的角色列表
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	// 帳號是否過期
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 帳號是否鎖定
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 密碼是否過期
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 是否開帳
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 返回上次密碼重置日期
	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public String getMail() {
		return mail;
	}
}
