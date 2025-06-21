package com.example.tasks.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskGroupDTO(
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
        String name,

        @NotNull(message = "Board ID é obrigatório")
        Long boardId
) {}