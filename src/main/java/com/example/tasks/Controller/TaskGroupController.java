package com.example.tasks.Controller;

import com.example.tasks.Dto.TaskGroupDto;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @PostMapping
    public ResponseEntity<TaskGroup> criarTaskGroup(@RequestBody TaskGroupDto dto) {
        TaskGroup created = taskGroupService.criarTaskGroup(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> listarTaskGroups() {
        return ResponseEntity.ok(taskGroupService.listarTaskGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(taskGroupService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> atualizarTaskGroup(@PathVariable Long id, @RequestBody TaskGroupDto dto) {
        return ResponseEntity.ok(taskGroupService.atualizarTaskGroup(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTaskGroup(@PathVariable Long id) {
        taskGroupService.deletarTaskGroup(id);
        return ResponseEntity.noContent().build();
    }
}