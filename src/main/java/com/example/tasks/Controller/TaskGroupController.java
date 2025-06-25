package com.example.tasks.Controller;

import com.example.tasks.Controller.TaskGroupResponseDTO;
import com.example.tasks.Model.TaskGroup;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {

    @PostMapping
    public TaskGroupResponseDTO createTaskGroup(@Valid @RequestBody TaskGroup taskGroup) {
        // Montar o DTO de resposta
        TaskGroupResponseDTO response = new TaskGroupResponseDTO();
        response.setName(taskGroup.getName());

        return response;
    }
}
