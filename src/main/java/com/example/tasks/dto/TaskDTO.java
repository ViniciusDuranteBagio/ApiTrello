package com.example.tasks.dto;

import com.example.tasks.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDTO {
    private Long id;

    @NotBlank(message = "O título da task é obrigatório.")
    private String title;

    private String description;

    @NotNull(message = "O status da task é obrigatório.")
    private TaskStatus status;

    @NotNull(message = "O ID do grupo de tarefas é obrigatório.")
    private Long taskGroupId;
}