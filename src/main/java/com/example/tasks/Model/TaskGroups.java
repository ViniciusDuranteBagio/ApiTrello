package com.example.tasks.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "taskgroups")
public class TaskGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taskgroup", unique = true)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_board")
    private Board board;
    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;
    @OneToMany(mappedBy = "taskGroups", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Task> taskList;
    //Constructors
    public TaskGroups() {
        this.dtCriacao = LocalDate.now();
    }
    public TaskGroups (String nome, String descricao, Board board) {
        this.nome = nome;
        this.board = board;
        this.dtCriacao = LocalDate.now();
    }

    //Gets & Sets

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}


