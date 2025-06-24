package com.example.trelloapi.Controller;

import com.example.trelloapi.Model.TaskGroup;
import com.example.trelloapi.Service.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskgroups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping
    public List<TaskGroup> getAllTaskGroups(@RequestParam(required = false) Long boardId) {
        if (boardId != null) {
            return taskGroupService.findByBoardId(boardId);
        }
        return taskGroupService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskGroup> createTaskGroup(@RequestBody TaskGroup taskGroup) {
        try {
            TaskGroup createdTaskGroup = taskGroupService.save(taskGroup);
            return ResponseEntity.ok(createdTaskGroup);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id, @RequestBody TaskGroup taskGroupDetails) {
        try {
            TaskGroup updatedTaskGroup = taskGroupService.update(id, taskGroupDetails);
            return ResponseEntity.ok(updatedTaskGroup);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
