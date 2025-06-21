package com.example.tasks.dto;

import com.example.tasks.Model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreateDTO {

    @NotBlank(message = "O título da Task é obrigatório")
    private String title;

    private String description;

    @NotNull(message = "O status é obrigatório")
    private TaskStatus status;

    @NotNull(message = "O ID do TaskGroup é obrigatório")
    private Long taskGroupId;
}