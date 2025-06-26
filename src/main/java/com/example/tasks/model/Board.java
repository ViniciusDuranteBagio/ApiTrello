package com.example.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    // Um Board tem muitos TaskGroups.
    // cascade = CascadeType.ALL: Operações (persist, remove, etc.) no Board são cascateadas para os TaskGroups.
    // orphanRemoval = true: Se um TaskGroup for removido da lista, ele será deletado do banco.
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskGroup> taskGroups = new ArrayList<>();
}
