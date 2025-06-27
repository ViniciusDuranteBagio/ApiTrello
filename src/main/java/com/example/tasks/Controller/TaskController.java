package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /tasks
    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /tasks?taskGroupId=
    @PostMapping
    public Task create(
            @Valid @RequestBody Task task,
            @RequestParam Long taskGroupId
    ) {
        return service.create(task, taskGroupId);
    }

    // PUT /tasks/{id}
    @PutMapping("/{id}")
    public Task update(
            @PathVariable Long id,
            @Valid @RequestBody Task task
    ) {
        return service.update(id, task);
    }

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}