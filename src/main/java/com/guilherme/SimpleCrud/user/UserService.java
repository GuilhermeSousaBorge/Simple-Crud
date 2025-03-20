package com.guilherme.SimpleCrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserCreateRequestDTO payload) throws Exception {

        var encodedPass = this.passwordEncoder.encode(payload.password());

        var entity = this.userMapper.toEntity(payload);
        
        entity.setPassword(encodedPass);
        return this.userMapper.toUserResponseDTO(this.userRepository.save(entity));
    }

    public User details(UUID id) throws Exception{

        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));
        return user;

    }

    public List<UserResponseDTO> findAll(){

        var userList = this.userRepository.findAll().stream().map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build()
                )
                .toList();
        return userList;
    }

    public UUID updateUser(UUID id, User payload) throws Exception {
        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));

        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPassword(payload.getPassword());
        user.setCreatedAt(payload.getCreatedAt());
        user.setUpdatedAt(payload.getUpdatedAt());
        var response = this.userRepository.save(user);

        return response.getId();
    }
    public void deleteUser(UUID id) throws Exception{

        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));
        this.userRepository.delete(user);
    }
}
