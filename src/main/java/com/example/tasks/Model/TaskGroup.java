package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;

//Criação de construtures
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TaskGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Bord_Id", nullable = false)
    private BoardModel BoardId;

    private ArrayList<TaskModel> tasks;
}
