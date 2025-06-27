package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasksGrop")
public class TaskGroupController {

    private TaskGroupService taskGroupService;

    @PostMapping
    public ResponseEntity<TaskGroup> createTaskGroup(@RequestBody @Valid TaskGroup taskGroup) {
        try {
            TaskGroup created = taskGroupService.create(taskGroup);
            return ResponseEntity.status(201).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // ou usar um handler global depois
        }
    }

    @GetMapping("/summary")
    public List<Map<String, Object>> getTaskGroupSummaries() {
        List<TaskGroup> groups = taskGroupService.getAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (TaskGroup group : groups) {
            Map<String, Object> groupData = new HashMap<>();
            groupData.put("name", group.getName());
            response.add(groupData);
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGruopId(@PathVariable Long id) {
        return taskGroupService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id, @RequestBody @Valid TaskGroup taskGroup) {
        try {
            TaskGroup updated = taskGroupService.update(id, taskGroup);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /boards/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
