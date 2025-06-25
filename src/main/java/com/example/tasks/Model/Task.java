package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    @Column(nullable = false)
    private String title;

    private String description;

    @NotNull(message = "O status não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "O TaskGroup não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "task_group_id", nullable = false)
    @JsonBackReference
    private TaskGroup taskGroup;


}
