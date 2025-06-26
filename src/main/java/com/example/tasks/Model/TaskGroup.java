package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_Group")
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "não pode está null.")
    private String nome;
    @OneToMany(mappedBy = "taskGroup",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> TaskList;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Boards board;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boards getBoard() {
        return board;
    }

    public void setBoard(Boards board) {
        this.board = board;
    }

    public List<Task> getTaskList() {
        return TaskList;
    }

    public void setTaskList(List<Task> taskList) {
        TaskList = taskList;
    }

    public @NotBlank(message = "não pode está null.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "não pode está null.") String nome) {
        this.nome = nome;
    }
}
