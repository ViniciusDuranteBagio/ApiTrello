package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-groups")
public class TaskGroupController {
    private final TaskGroupService service;

    public TaskGroupController(TaskGroupService service) {
        this.service = service;
    }

    // Lista todos os TaskGroups
    @GetMapping
    public List<TaskGroup> getAll() {
        return service.getAll();
    }

    // Busca TaskGroup por ID
    @GetMapping("/{id}")
    public TaskGroup getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Cria um TaskGroup associado a um Board (passar boardId na query)
    @PostMapping
    public TaskGroup create(
            @Valid @RequestBody TaskGroup taskGroup,
            @RequestParam Long boardId
    ) {
        return service.create(taskGroup, boardId);
    }

    // Atualiza nome do TaskGroup
    @PutMapping("/{id}")
    public TaskGroup update(
            @PathVariable Long id,
            @Valid @RequestBody TaskGroup taskGroup
    ) {
        return service.update(id, taskGroup);
    }

    // Remove TaskGroup por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}