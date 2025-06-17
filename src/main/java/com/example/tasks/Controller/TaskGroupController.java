package com.example.tasks.Controller;

import com.example.tasks.Dtos.TaskGroupDto;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TaskGroup>> listarTodos(){
        List<TaskGroup> taskGroups = taskGroupService.listarTodos();
        return ResponseEntity.ok(taskGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return taskGroupService.buscarPorId(id).map(taskGroup -> ResponseEntity.ok(taskGroup))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarBoard(@PathVariable Long id, @Valid @RequestBody TaskGroupDto taskGroupDto){
        return  taskGroupService.atualizarTaskGroup(id, taskGroupDto)
                .map(taskGroupAtualizado -> ResponseEntity.ok(taskGroupAtualizado))
                .orElse(ResponseEntity.notFound().build());
    }



}
