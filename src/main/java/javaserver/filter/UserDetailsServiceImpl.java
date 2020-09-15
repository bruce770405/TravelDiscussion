package javaserver.filter;

import javaserver.entity.LoginEntity;
import javaserver.repository.UserJpaRepository;
import javaserver.util.AuthUserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final UserJpaRepository repository;

    public UserDetailsServiceImpl(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginEntity login = repository.findByUsername(username);
        if (!Objects.isNull(login)) {
            if (login.getStopTag() == 1) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
            return AuthUserFactory.create(login);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }

}
