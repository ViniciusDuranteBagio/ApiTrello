package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "O nome do Board é obrigatório")
    @Size(min = 3, message = "O nome do Board deve ter no mínimo 3 caracteres")
    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<TaskGroup> taskGroups;



}
