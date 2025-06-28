package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskGroup {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "O campo 'name' não pode estar em branco")
    @Size(min = 3)
    private String name;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL)
    private List<Task> tasksList;

    @ManyToOne
    @JoinColumn(name = "board_id") // coluna no banco que faz a ligação com o Board
    private Board board;

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

    public List<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

}