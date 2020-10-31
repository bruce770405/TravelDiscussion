package javaserver.service.impl;

import javaserver.entity.UserEntity;
import javaserver.repository.UserRepository;
import javaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Resource getUsersIcon(String username) {
        Optional<UserEntity> optional = userRepository.findByUsername(username);
        UserEntity userEntity = optional.orElseThrow(() -> new UsernameNotFoundException("No user's icon found"));
        byte[] icon = Base64.getDecoder().decode(userEntity.getIcon());
        return new ByteArrayResource(icon);
    }
}
