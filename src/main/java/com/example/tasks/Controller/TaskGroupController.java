package com.example.tasks.Controller;

import com.example.tasks.Dto.TaskGroup.TaskGroupCreateDto;
import com.example.tasks.Dto.TaskGroup.TaskGroupUpdateDto;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/taskGroups")
public class TaskGroupController {
    @Autowired
    private TaskGroupService taskGroupService;

    @GetMapping("/all")
    public ResponseEntity<Page<TaskGroup>> getAllTasks(Pageable pageable) {
        return ResponseEntity.ok(taskGroupService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskById(@PathVariable Long id) {
        return taskGroupService.findById(id)
                .map(ResponseEntity::ok).orElse(
                        ResponseEntity.notFound().build()
                );
    }

    @PostMapping()
    public ResponseEntity<TaskGroup> postTasks(@Valid @RequestBody TaskGroupCreateDto taskGroupDto) {
        return ResponseEntity.ok(taskGroupService.create(taskGroupDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> putTasks(@PathVariable Long id, @Valid @RequestBody TaskGroupUpdateDto taskGroupDto) {
        TaskGroup updated = taskGroupService.update(id, taskGroupDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskGroupService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
