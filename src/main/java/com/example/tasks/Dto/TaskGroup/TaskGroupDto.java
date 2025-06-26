package com.example.tasks.Dto.TaskGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskGroupDto(@NotBlank String name, @NotNull Long id) {
}
