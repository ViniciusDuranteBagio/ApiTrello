package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task-groups")
@RequiredArgsConstructor
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody TaskGroup taskGroup) {
        try {
            return ResponseEntity.ok(taskGroupService.create(taskGroup));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<TaskGroup> findAll() {
        return taskGroupService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(taskGroupService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable Long id, @Valid @RequestBody TaskGroup taskGroup) {
        try {
            return ResponseEntity.ok(taskGroupService.update(id, taskGroup));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        try {
            taskGroupService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task Group removido.");
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Task Group não encontrado.");
            return ResponseEntity.status(400).body(response);
        }
    }
}
