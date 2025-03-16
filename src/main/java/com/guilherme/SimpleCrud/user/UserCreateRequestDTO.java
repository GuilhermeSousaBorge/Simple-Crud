package com.guilherme.SimpleCrud.user;

public record UserCreateRequestDTO(
        String name,
        String email,
        String password
) {
}
