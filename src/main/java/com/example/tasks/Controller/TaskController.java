package com.example.tasks.Controller;

import com.example.tasks.Dto.TaskDto;
import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> criarTask(@RequestBody TaskDto taskDto) {
        Task createdTask = taskService.criarTask(taskDto);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> listarTasks() {
        return ResponseEntity.ok(taskService.listarTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizarTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.atualizarTask(id, taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTask(@PathVariable Long id) {
        taskService.deletarTask(id);
        return ResponseEntity.noContent().build();
    }
}