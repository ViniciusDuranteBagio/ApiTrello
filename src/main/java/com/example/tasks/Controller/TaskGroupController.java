package com.example.tasks.Controller;

import com.example.tasks.Dtos.TaskGroupDto;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping("/{boardId}")
    public ResponseEntity<TaskGroup> criarTaskGroup(@PathVariable Long boardId, @Valid @RequestBody TaskGroupDto taskGroupDto) {
       TaskGroup criado = taskGroupService.criarTaskGroup(boardId, taskGroupDto );
       return ResponseEntity.ok(criado);
    }
}
