package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor  //*
@NoArgsConstructor //*
@Entity
@Table(name = "board", schema = "api_trello")
public class Board {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "board_id")
    private Long boardId;

    @NotEmpty
    @Size(min = 3, max = 100, message = "O nome do quadro deve ter entre 3 e 100 caracteres")
    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_description")
    private String boardDescription;

    @NotEmpty
    @Column(name = "board_group")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private String boardGroup;

}
