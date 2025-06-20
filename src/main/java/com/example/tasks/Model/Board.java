package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "board_id", unique = true)
    private Long boardId;

    @Size(min = 3)
    @Column(name = "board_name")
    @NotBlank(message = "O nome não pode estar vazio")
    private String boardName;

    @Column(name = "board_description")
    private String boardDescription;

    @Column(name = "board_task_group")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskGroup> taskGroups = new ArrayList<>();
}
