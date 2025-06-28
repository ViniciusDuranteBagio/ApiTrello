package com.example.tasks.Controller;

import com.example.tasks.DTO.TaskDTO;
import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.listAll();
        List<TaskDTO> dtos = tasks.stream().map(TaskDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(new TaskDTO(task));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO dto) {
        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        task.setTaskGroup(taskGroupService.findById(dto.getTaskGroupId()));

        Task saved = taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO dto) {
        Task existing = taskService.findById(id);
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setStatus(dto.getStatus());
        existing.setTaskGroup(taskGroupService.findById(dto.getTaskGroupId()));

        Task updated = taskService.update(id, existing);
        return ResponseEntity.ok(new TaskDTO(updated));
    }
}