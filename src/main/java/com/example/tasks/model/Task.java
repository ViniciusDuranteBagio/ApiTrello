package com.example.tasks.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    // --- LINHA CORRIGIDA ---
    // Em vez de um Long taskGroupId, temos o objeto TaskGroup.
    // O nome do campo agora é "taskGroup" (com 'g' minúsculo),
    // que é o que o 'mappedBy' na outra classe estava procurando.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_group_id", nullable = false)
    private TaskGroup taskGroup;
}