package com.example.tasks.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;

//Criação de construtures
@Data
@NoArgsConstructor
@AllArgsConstructor


public class BoardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres")
    private String name;
    private String description;
    private ArrayList<TaskGroupModel> taskGrop;
}
