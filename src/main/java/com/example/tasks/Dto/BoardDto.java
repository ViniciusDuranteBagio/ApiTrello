// src/main/java/com/example/tasks/Dto/BoardDto.java
package com.example.tasks.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    @NotBlank
    @Size(min = 3)
    private String name;
    private String description;
}