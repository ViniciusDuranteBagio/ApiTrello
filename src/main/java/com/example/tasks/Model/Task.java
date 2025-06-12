package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório.")
    private String name;

    private String description;

    @NotBlank(message = "Status é obrigatório.")
    private String status;

    @ManyToOne
    @JoinColumn(name = "task_group_id", nullable = false)
    private com.example.tasks.Model.TaskGroup taskGroup;

}
