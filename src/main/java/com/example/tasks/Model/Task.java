package com.example.tasks.Model;

import com.example.tasks.enums.StatusValidos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private StatusValidos status;

    @ManyToOne
    @JoinColumn(name = "taskGroup_id")
    private TaskGroup taskGroup;


}
