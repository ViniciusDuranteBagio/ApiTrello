package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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

@Table(name = "task_group")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_group_id", unique = true)
    private long taskGroupId;

    @Column(name = "task_group_name")
    @NotEmpty
    @Size(min = 3)
    private String taskGroupName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();




}
