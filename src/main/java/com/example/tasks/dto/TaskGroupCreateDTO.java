package com.example.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGroupCreateDTO {

    @NotBlank(message = "O nome do TaskGroup é obrigatório")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
    private String name;

    @NotNull(message = "O ID do Board é obrigatório")
    private Long boardId;
}