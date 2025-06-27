package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taskGroup")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping
    public TaskGroup postTaskGroup(@Valid @RequestBody TaskGroup taskGroup){
        return taskGroupService.saveTaskGroup(taskGroup);
    }

    @GetMapping("/all")
    public List<TaskGroup> getAllTaskGroups (){
        return taskGroupService.getAllTaskGroups();
    }

    @GetMapping("/{id}")
    public Optional<TaskGroup> getTaskGroupById(@PathVariable Long id) {
        return taskGroupService.getTaskGroupById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> updateTaskGroup(@PathVariable Long id, @RequestBody TaskGroup updatedTaskGroup) {
        TaskGroup updated = taskGroupService.updateTaskGroup(id, updatedTaskGroup);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskGroupService.deleteTaskGroup(id);
        return ResponseEntity.noContent().build();
    }
}
