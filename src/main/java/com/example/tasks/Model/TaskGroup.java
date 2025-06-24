package com.example.tasks.Model;

import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do grupo não pode estar em branco")
    @Size(min = 3, message = "O nome do TaskGroup deve ter no mínimo 3 caracteres")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "taskGroup", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (this.board == null) {
            throw new ValidationException("TaskGroup deve pertencer a um Board");
        }
    }

}
