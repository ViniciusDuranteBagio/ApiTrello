package com.example.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BoardDTO {
    private Long id;

    @NotBlank(message = "O nome do board é obrigatório.")
    @Size(min = 3, message = "O nome do board deve ter no mínimo 3 caracteres.")
    private String name;

    private String description;
}

