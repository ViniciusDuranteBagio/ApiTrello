package com.example.tasks.Controller;

import com.example.tasks.Dto.Task.TaskCreateDto;
import com.example.tasks.Dto.Task.TaskDto;
import com.example.tasks.Dto.TaskGroup.TaskGroupUpdateDto;
import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskCreateDto dto) {
        Task created = taskService.create(dto);
        return ResponseEntity.status(201).body(created);
    }
    @GetMapping
    public ResponseEntity<Page<Task>> getAll(Pageable pageable) {
        return ResponseEntity.ok(taskService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody TaskDto dto) {
        Task updated = taskService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}