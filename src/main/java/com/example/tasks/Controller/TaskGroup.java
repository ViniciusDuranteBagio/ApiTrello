package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.Task;
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
    @NotEmpty
    @Column(name = "task_group_id")
    private Long taskGroupId;

    @NotEmpty
    @Size(min = 3, max = 100, message = "O nome do deve ter entre 3 e 100 caracteres")
    @Column(name = "task_group_title")
    private String taskGroupTitle;

    @ManyToOne(optional = false) //*
    @JoinColumn(name = "board_id") // *
    private Board board;

    @ManyToMany(mappedBy = "taskGroups", cascade = CascadeType.ALL)
    @Column(name = "task_group_description")
    private List<Task> tasks = new ArrayList<>();
}
