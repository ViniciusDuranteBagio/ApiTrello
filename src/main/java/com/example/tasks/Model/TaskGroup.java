package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do TaskGroup é obrigatório")
    @Size(min = 3, message = "O nome do TaskGroup deve ter no mínimo 3 caracteres")
    private String name;

    // Muitos TaskGroups pertencem a um Board
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // Chave estrangeira na tabela TaskGroup
    @JsonBackReference // Evita recursão infinita
    private Board board;

    // Um TaskGroup tem muitas Tasks
    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Evita recursão infinita
    private List<Task> tasks = new ArrayList<>();
}