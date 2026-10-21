package io.github.kl.planning_api.domain.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersRegisterForm(
        @NotBlank(message = "Informe o nome do usuário")
        String name,
        @NotBlank(message = "Informe o login")
        String login,
        @NotBlank(message = "Informe a senha")
        @Size(min = 8, message = "A senha deve ter pelo menos 6 caracteres")
        String password
) {
}
