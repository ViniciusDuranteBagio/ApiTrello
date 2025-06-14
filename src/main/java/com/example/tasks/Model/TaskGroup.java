package com.example.tasks.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "O minimo de caracteres deve ser 3")
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany
    private List<Task> listaDeTasks;


}
