package com.example.tasks.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, message = "O minimo de caracteres deve ser 3")
    private String name;

    private String description;
}
