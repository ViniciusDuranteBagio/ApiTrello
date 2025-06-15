package com.example.tasks.Dtos;

import com.example.tasks.Model.Board;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGroupDto {

    @Size(min = 3, message = "O minimo de caracteres deve ser 3")
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
