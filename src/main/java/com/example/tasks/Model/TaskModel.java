package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

//Criação de construtures
@Data
@NoArgsConstructor
@AllArgsConstructor


public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TaskGroup_Id", nullable = false)
    private TaskGroup taskGroupId;

    private enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
