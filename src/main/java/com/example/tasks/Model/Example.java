package com.example.tasks.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Example {
    @NotBlank
    private String message;
}
