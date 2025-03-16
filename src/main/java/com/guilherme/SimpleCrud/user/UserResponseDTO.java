package com.guilherme.SimpleCrud.user;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
