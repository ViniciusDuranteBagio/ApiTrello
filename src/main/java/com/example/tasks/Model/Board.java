package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_board", unique = true)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<TaskGroups> taskGroupsList;
    //Constructors
    public Board() {
        this.dtCriacao = LocalDate.now();
    }
    public Board (String nome, String descricao) {
        this.nome = nome;
       this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String decricao) {
        this.descricao = decricao;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public List<TaskGroups> getTaskGroupsList() {
        return taskGroupsList;
    }
    public void setTaskGroupsList(List<TaskGroups> taskGroupsList) {
        this.taskGroupsList = taskGroupsList;
    }
}



