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
@RequestMapping("/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping("/board/{boardId}")
    public ResponseEntity<TaskGroup> create(@PathVariable Long boardId, @Valid @RequestBody TaskGroup taskGroup) {
        return new ResponseEntity<>(taskGroupService.create(boardId, taskGroup), HttpStatus.CREATED);
    }

    @GetMapping
    public List<TaskGroup> all() {
        return taskGroupService.findAll();
    }

    @GetMapping("/{id}")
    public TaskGroup one(@PathVariable Long id) {
        return taskGroupService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskGroup update(@PathVariable Long id, @Valid @RequestBody TaskGroup taskGroup) {
        return taskGroupService.update(id, taskGroup);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskGroupService.delete(id);
    }
}