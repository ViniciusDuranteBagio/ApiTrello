package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/group/{taskGroupId}")
    public ResponseEntity<Task> create(@PathVariable Long taskGroupId, @Valid @RequestBody Task task) {
        return new ResponseEntity<>(taskService.create(taskGroupId, task), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Task> all() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task one(@PathVariable Long id) {
        return taskService.findById(id);
    }


    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}