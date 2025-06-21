package com.example.tasks.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do Board é obrigatório")
    @Size(min = 3, message = "O nome do Board deve ter no mínimo 3 caracteres")
    private String name;

    private String description;

    // Um Board tem muitos TaskGroups
    // mappedBy = "board" indica que a entidade TaskGroup é a dona do relacionamento
    // cascade = CascadeType.ALL significa que se um Board for deletado, seus TaskGroups também serão.
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Evita recursão infinita ao converter para JSON
    private List<TaskGroup> taskGroups = new ArrayList<>();
}