package com.example.tasks.Dto.TaskGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskGroupCreateDto(@NotBlank(message = "Nome é obrigatório")
                                 String name,
                                 @NotNull Long boardId) {
}
