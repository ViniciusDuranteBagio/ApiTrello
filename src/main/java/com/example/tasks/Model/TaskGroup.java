package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//Criação de construtures
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Bord_Id", nullable = false)
    private Board BoardId;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}
