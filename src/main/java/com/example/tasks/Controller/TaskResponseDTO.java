package com.example.tasks.Controller;

import com.example.tasks.Model.TaskStatus;
import lombok.Data;

@Data
public class TaskResponseDTO {
    private String title;
    private String description;
    private TaskStatus status;
}
