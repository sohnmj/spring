package com.example.oauth2authorization.service;

import com.example.oauth2authorization.dto.UserDTO;
import com.example.oauth2authorization.entity.UserEntity;
import com.example.oauth2authorization.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Transactional
    public void join(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .role("ADMIN")
                .phone(userDTO.getPhone())
                .build();
        userRepository.save(userEntity);
    }
}
