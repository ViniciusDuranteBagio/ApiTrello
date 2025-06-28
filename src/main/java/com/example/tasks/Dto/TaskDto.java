package com.example.tasks.dto;

import com.example.tasks.model.Task.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;

    @NotBlank(message = "O título da task é obrigatório")
    private String title;

    private String description;

    @NotNull(message = "O status é obrigatório")
    private Status status;

    @NotNull(message = "O ID do task group é obrigatório")
    private Long taskGroupId;
}
