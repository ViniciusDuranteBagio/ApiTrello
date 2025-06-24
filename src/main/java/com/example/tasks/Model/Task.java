package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "task")
public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "task_id", unique = true)
        private Long taskId;

        @NotBlank(message = "O título é obrigatório.")
        @Size(min = 3 , message = "O título deve ter no mínimo 3 caracteres.")
        @Column(name = "task_title")
        private String taskTitle;

        @Column(name = "task_description")
        private String taskDescription;

        @NotNull(message = "O status é obrigatório.")
        @Enumerated(EnumType.STRING)
        @Column(name = "task_status", nullable = false)
        private taskStatus taskStatus;

        @ManyToOne
        @JoinColumn(name = "task_group_id")
        @JsonBackReference
        private TaskGroup taskGroup;

        public enum taskStatus {
                TODO,
                IN_PROGRESS,
                DONE;
        }

}


