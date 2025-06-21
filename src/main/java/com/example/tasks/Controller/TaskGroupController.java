package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupService.findAllTaskGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.findGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id,
            @Valid @RequestBody TaskGroup groupDetails) {
        try {
            TaskGroup updatedGroup = taskGroupService.updateTaskGroup(id, groupDetails);
            return ResponseEntity.ok(updatedGroup);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        try {
            taskGroupService.deleteTaskGroup(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}