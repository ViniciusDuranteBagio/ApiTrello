package com.example.tasks.Controller;

import com.example.tasks.Model.Status;
import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        try {
            Task task = service.findById(id);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) {
        try {
            Task savedTask = service.save(task);
            return ResponseEntity.status(201).body(savedTask);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid Task task) {
        try {
            Task existingTask = service.findById(id);
            task.setId(id);
            task.setTaskGroup(existingTask.getTaskGroup());
            Task updatedTask = service.save(task);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/task-group/{taskGroupId}")
    public List<Task> getByTaskGroupId(@PathVariable Long taskGroupId) {
        return service.findByTaskGroupId(taskGroupId);
    }

    @GetMapping("/status/{status}")
    public List<Task> getByStatus(@PathVariable Status status) {
        return service.findByStatus(status);
    }
}
