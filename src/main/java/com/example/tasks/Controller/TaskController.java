package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
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
    public Task getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Task create(@RequestBody @Valid Task task) {
        return service.save(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
