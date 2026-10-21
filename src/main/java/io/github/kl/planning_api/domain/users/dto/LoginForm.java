package io.github.kl.planning_api.domain.users.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
        @NotBlank(message = "Informe o login")
        String login,
        @NotBlank(message = "Informe a senha")
        String password
) { }
