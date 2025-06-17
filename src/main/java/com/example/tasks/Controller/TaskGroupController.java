package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping
    public ResponseEntity<TaskGroup> createTaskGroup(@Valid @RequestBody TaskGroup taskGroup) {
        return ResponseEntity.ok(taskGroupService.create(taskGroup));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> getAllTaskGroups() {
        return ResponseEntity.ok(taskGroupService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(taskGroupService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id, @Valid @RequestBody TaskGroup taskGroup) {
        return ResponseEntity.ok(taskGroupService.update(id, taskGroup));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
