package javaserver.filter;

import javaserver.entity.UserEntity;
import javaserver.repository.UserRepository;
import javaserver.util.AuthUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = repository.findByUsername(username);
        UserEntity userEntity = optional.orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
        if (userEntity.getStopTag() == 1) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        return AuthUserFactory.create(userEntity);
    }

}
