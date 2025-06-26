package com.example.tasks.Dto.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDto(@NotNull Long id,
                      @NotBlank String name,
                      String description,
                      String status,
                      @NotNull Long taskGroupId) {
}
