package com.example.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskGroupDTO {
    private Long id;

    @NotBlank(message = "O nome do grupo é obrigatório.")
    @Size(min = 3, message = "O nome do grupo deve ter no mínimo 3 caracteres.")
    private String name;

    @NotNull(message = "O ID do board é obrigatório.")
    private Long boardId;
}