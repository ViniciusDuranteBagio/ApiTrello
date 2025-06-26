package com.example.tasks.Dto.TaskGroup;

import jakarta.validation.constraints.NotNull;

public record TaskGroupUpdateDto (String name,
                                  @NotNull Long boardId) {
}
