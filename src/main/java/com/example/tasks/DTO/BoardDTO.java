package com.example.tasks.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BoardDTO(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
        String name,

        String description
) {}