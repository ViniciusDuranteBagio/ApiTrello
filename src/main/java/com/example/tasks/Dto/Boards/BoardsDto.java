package com.example.tasks.Dto.Boards;

import jakarta.validation.constraints.NotBlank;

public record BoardsDto(@NotBlank String name,String description) {
}
