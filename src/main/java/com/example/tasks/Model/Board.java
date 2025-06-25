package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 500)
    private String description;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TaskGroup> taskGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskGroup> getTaskGroups() {
        return taskGroups;
    }

    public void setTaskGroups(List<TaskGroup> taskGroups) {
        this.taskGroups = taskGroups;
    }
}
