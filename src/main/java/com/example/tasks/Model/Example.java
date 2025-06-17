package com.example.tasks.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class Example {

    @NotEmpty(message = "O campo 'name' não pode estar em branco")
    private String name;

    public Example() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
