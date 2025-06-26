package com.example.tasks.Model;

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
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "não pode ser vazio")
    @Size(min=3,max=255,message = "Minino 3 até 255 caracter.")
    private String name;
    private String description;
    private String status;
    @ManyToOne(optional = false)
    @JoinColumn(name = "taskGroup_id")
    private TaskGroup taskGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "não pode ser vazio") @Size(min = 3, max = 255, message = "Minino 3 até 255 caracter.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "não pode ser vazio") @Size(min = 3, max = 255, message = "Minino 3 até 255 caracter.") String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskGroup getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }
}
