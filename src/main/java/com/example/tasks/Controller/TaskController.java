package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

//    @GetMapping("/all")
//    public List<Task> getAllTasks() {
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(new Task("name", "description", "status"));
//        return tasks;
//    }

    @GetMapping("/summary")
    public List<Map<String, Object>> getTaskSummaries() {
        List<Task> tasks = taskService.getAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Task task : tasks) {
            Map<String, Object> taskData = new HashMap<>();
            taskData.put("name", task.getName());
            taskData.put("status", task.getStatus());
            taskData.put("description", task.getDescription());
            response.add(taskData);
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) {
        try {
            Task created = taskService.create(task);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        return taskService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
