package com.example.tasks.dto;

import com.example.tasks.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Usar records para DTOs é uma prática moderna em Java,
// eles são imutáveis e concisos.
public class Dtos {

    // DTO para criar e atualizar um Board
    public record BoardRequest(
            @NotBlank(message = "O nome não pode ser vazio.")
            @Size(min = 3, message = "O nome deve conter no mínimo 3 caracteres.")
            String name,

            String description
    ) {}

    // DTO para a resposta da API sobre um Board
    public record BoardResponse(
            Long id,
            String name,
            String description
    ) {}

    // DTO para criar e atualizar um TaskGroup
    public record TaskGroupRequest(
            @NotBlank(message = "O nome não pode ser vazio.")
            @Size(min = 3, message = "O nome deve conter no mínimo 3 caracteres.")
            String name,

            @NotNull(message = "O ID do board é obrigatório.")
            Long boardId
    ) {}

    // DTO para a resposta da API sobre um TaskGroup
    public record TaskGroupResponse(
            Long id,
            String name,
            Long boardId
    ) {}

    // DTO para criar e atualizar uma Task
    public record TaskRequest(
            @NotBlank(message = "O título não pode ser vazio.")
            String title,

            String description,

            @NotNull(message = "O status é obrigatório.")
            TaskStatus status,

            @NotNull(message = "O ID do grupo de tarefas é obrigatório.")
            Long taskGroupId
    ) {}

    // DTO para a resposta da API sobre uma Task
    public record TaskResponse(
            Long id,
            String title,
            String description,
            TaskStatus status,
            Long taskGroupId
    ) {}
}
