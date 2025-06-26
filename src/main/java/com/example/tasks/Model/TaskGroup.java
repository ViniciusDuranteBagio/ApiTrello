package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "task_group", schema = "api_trello")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_group_id")
    private long taskGroupId;

    @Column(name = "task_group_name")
    @NotEmpty
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String taskGroupName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true) //
    @Column(name = "task_group_tasks")
    private List<Task> tasks = new ArrayList<>();
}
