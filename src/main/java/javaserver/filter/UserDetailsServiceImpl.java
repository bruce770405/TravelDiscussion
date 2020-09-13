package javaserver.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javaserver.entity.LoginData;
import javaserver.repository.UserJpaRepository;
import javaserver.util.AuthUserFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserJpaRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginData login = repository.findByUsername(username);
		if(login != null) {
			login.setUsername(login.getUsername().trim());
			return AuthUserFactory.create(login);	
		}else {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));	
		}
	}

}
