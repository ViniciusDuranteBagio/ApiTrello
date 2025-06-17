package com.example.tasks.Model;

import com.example.tasks.Controller.TaskGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task", schema = "api_trello")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "task_id")
    private Long taskId;

    @NotEmpty
    @Size(min = 3, max = 100, message = "O nome do deve ter entre 3 e 100 caracteres")
    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_description")
    private String taskDescription;

    @Enumerated(EnumType.STRING) // *
    @Column(name = "task_status", nullable = false)
    private String taskStatus;

    @ManyToOne(optional = false) // *
    @JoinColumn(name = "task_group_id") // *
    private TaskGroup taskGroup;
}