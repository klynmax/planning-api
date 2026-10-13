package io.github.kl.planning_api.domain.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryForm(@NotBlank(message = "Campo obrigatório") String name) {
}
