package com.example.tasks.controller;

import com.example.tasks.dto.TaskGroupDto;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.service.TaskGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-groups")
@RequiredArgsConstructor
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TaskGroupDto dto) {
        return taskGroupService.create(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Board informado não existe."));
    }

    @GetMapping
    public ResponseEntity<List<TaskGroup>> findAll() {
        return ResponseEntity.ok(taskGroupService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> findById(@PathVariable Long id) {
        return taskGroupService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TaskGroupDto dto) {
        return taskGroupService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Board informado não existe ou TaskGroup não encontrado."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return taskGroupService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
