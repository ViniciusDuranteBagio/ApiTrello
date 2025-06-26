package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "board")
@AllArgsConstructor
@NoArgsConstructor
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "min 3 até 255 caracter")
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskGroup> taskGrouplist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TaskGroup> getTaskGrouplist() {
        return taskGrouplist;
    }

    public void setTaskGrouplist(List<TaskGroup> taskGrouplist) {
        this.taskGrouplist = taskGrouplist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotBlank(message = "min 3 até 255 caracter") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "min 3 até 255 caracter") String name) {
        this.name = name;
    }
}
