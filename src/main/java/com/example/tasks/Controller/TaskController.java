package com.example.tasks.Controller;

import com.example.tasks.Controller.TaskResponseDTO;
import com.example.tasks.Model.Task;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @PostMapping
    public TaskResponseDTO createTask(@Valid @RequestBody Task task) {
        // Monta o DTO manualmente
        TaskResponseDTO response = new TaskResponseDTO();
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());

        return response;
    }
}
