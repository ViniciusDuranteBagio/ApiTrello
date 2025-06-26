package com.example.tasks.Model;

import jakarta.validation.constraints.NotBlank;

public class Example {

    @NotBlank(message = "O campo 'name' não pode estar em branco")
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
