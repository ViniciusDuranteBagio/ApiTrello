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
    public ResponseEntity<TaskGroup> createTaskGroup(@RequestParam Long boardId,@Valid @RequestBody TaskGroup taskGroup) {
        return ResponseEntity.ok(taskGroupService.createTaskGroup(boardId, taskGroup));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> getAllTaskGroups() {
        return ResponseEntity.ok(taskGroupService.getAllTaskGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.getTaskGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id,@Valid @RequestBody TaskGroup taskGroupDetails) {
        return ResponseEntity.ok(taskGroupService.updateTaskGroup(id, taskGroupDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.deleteTaskGroup(id);
        return ResponseEntity.noContent().build();
    }

    //teste testando
}
