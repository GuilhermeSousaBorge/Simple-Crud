package com.guilherme.SimpleCrud.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserResponseDTO userResponseDTO){
        return User.builder()
                .id(userResponseDTO.id())
                .name(userResponseDTO.name())
                .email(userResponseDTO.email())
                .password(userResponseDTO.password())
                .createdAt(userResponseDTO.createdAt())
                .updatedAt(userResponseDTO.updatedAt())
                .build();
    }

    public User toEntity(UserCreateRequestDTO userCreateRequestDTO){
        return User.builder()
                .name(userCreateRequestDTO.name())
                .email(userCreateRequestDTO.email())
                .password(userCreateRequestDTO.password())
                .build();
    }

    public UserResponseDTO toUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
