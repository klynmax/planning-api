package io.github.kl.planning_api.domain.users.dto;

public record AuthResponse(
        String token,
        String type,
        long expiresIn,
        String name
) { }
