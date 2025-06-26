package com.example.tasks.controller;

import com.example.tasks.dto.Dtos;
import com.example.tasks.service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @PostMapping
    public ResponseEntity<Dtos.TaskGroupResponse> createTaskGroup(@Valid @RequestBody Dtos.TaskGroupRequest request) {
        Dtos.TaskGroupResponse response = taskGroupService.createTaskGroup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dtos.TaskGroupResponse>> getAllTaskGroups() {
        return ResponseEntity.ok(taskGroupService.getAllTaskGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dtos.TaskGroupResponse> getTaskGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(taskGroupService.getTaskGroupById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dtos.TaskGroupResponse> updateTaskGroup(@PathVariable Long id, @Valid @RequestBody Dtos.TaskGroupRequest request) {
        return ResponseEntity.ok(taskGroupService.updateTaskGroup(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.deleteTaskGroup(id);
        return ResponseEntity.noContent().build();
    }
}
