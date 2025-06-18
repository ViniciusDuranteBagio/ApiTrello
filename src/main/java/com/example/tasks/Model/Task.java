package com.example.tasks.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task", unique = true)
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "situacao")
    private Situacao situacao;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_taskgroup")
    private TaskGroups taskGroups;
    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;

    //Constructors
    public Task() {
        this.situacao = Situacao.PENDENTE;
        this.dtCriacao = LocalDate.now();
    }
    public Task(String titulo, String descricao, TaskGroups taskGroups) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.situacao = Situacao.PENDENTE;
        this.taskGroups = taskGroups;
        this.dtCriacao = LocalDate.now();
    }

    //Gets & Sets
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public TaskGroups getTaskGroups() {
        return taskGroups;
    }
    public void setTaskGroups(TaskGroups taskGroups) {
        this.taskGroups = taskGroups;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }


    //Enum status
    public enum Situacao {
        PENDENTE,
        INICIADO,
        PAUSADO,
        FINALIZADO,
        EXCLUIDO,
        DESCONTINUADO
    }
}

