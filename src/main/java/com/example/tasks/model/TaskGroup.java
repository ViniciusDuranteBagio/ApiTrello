package com.example.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Muitos TaskGroups pertencem a um Board.
    // fetch = FetchType.LAZY: O Board só será carregado do banco quando for acessado.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    // Um TaskGroup tem muitas Tasks.
    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();
}
