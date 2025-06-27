package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private long id;
    @NotBlank(message = "O título não pode estar em branco")
    @Size(min = 3, message = "O título deve ter no mínimo 3 caracteres")
    private String title;

    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "task_group_id", nullable = false)
    @JsonBackReference
    private TaskGroup taskGroup;

    public void updateTask (Task task) {
        setId(task.getId());
        setTitle(task.getTitle());
        setDescription(task.getDescription());
        setStatus(task.getStatus());
        setTaskGroup(task.getTaskGroup());
    }
}
