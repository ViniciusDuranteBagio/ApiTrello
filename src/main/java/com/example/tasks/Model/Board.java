package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Board {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "O campo 'name' não pode estar em branco")
    @Size(min = 3)
    private String name;
    private String description;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    public List<TaskGroup> boardList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskGroup> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<TaskGroup> boardList) {
        this.boardList = boardList;
    }

}