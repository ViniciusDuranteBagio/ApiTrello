package com.example.tasks.DTO;

import com.example.tasks.Model.Task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDTO(
        Long id,

        @NotBlank(message = "Título é obrigatório")
        String title,

        String description,

        @NotNull(message = "Status é obrigatório")
        TaskStatus status,

        @NotNull(message = "Task Group ID é obrigatório")
        Long taskGroupId
) {}