package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping
    public ResponseEntity<TaskGroup> create(@Valid @RequestBody TaskGroup group) {
        return ResponseEntity.ok(taskGroupService.createGroup(group));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> getAll() {
        return ResponseEntity.ok(taskGroupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getById(@PathVariable Long id) {
        return taskGroupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable Long id, @Valid @RequestBody TaskGroup group) {
        return taskGroupService.updateGroup(id, group)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (taskGroupService.deleteGroup(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

