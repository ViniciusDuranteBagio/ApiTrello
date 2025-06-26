package com.example.tasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
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

    // Muitas Tasks pertencem a um TaskGroup.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_group_id", nullable = false)
    private TaskGroup taskGroup;
}
