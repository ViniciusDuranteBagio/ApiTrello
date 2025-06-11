package com.example.tasks.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusValidos {
    TODO,
    IN_PROGESS,
    DONE;

    //Aceita letras maisculas e minusculas
    @JsonCreator
    public static StatusValidos fromString(String value) {
        return StatusValidos.valueOf(value.toUpperCase());
    }
}
