package com.example.tasks.Controller;

import com.example.tasks.DTO.TaskGroupDTO;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.BoardService;
import com.example.tasks.Service.TaskGroupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService taskGroupService;

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<TaskGroupDTO>> getAllTaskGroups() {
        List<TaskGroup> groups = taskGroupService.getAllTaskGroups();
        List<TaskGroupDTO> dtos = groups.stream().map(TaskGroupDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroupDTO> getTaskGroupById(@PathVariable Long id) {
        TaskGroup group = taskGroupService.findById(id);
        return ResponseEntity.ok(new TaskGroupDTO(group));
    }

    @PostMapping
    public ResponseEntity<TaskGroupDTO> createTaskGroup(@RequestBody @Valid TaskGroupDTO dto) {
        TaskGroup group = new TaskGroup();
        group.setName(dto.getName());

        // Busca o Board pela id
        group.setBoard(boardService.findById(dto.getBoardId()));

        TaskGroup saved = taskGroupService.create(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskGroupDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroupDTO> updateTaskGroup(@PathVariable Long id, @RequestBody @Valid TaskGroupDTO dto) {
        TaskGroup group = taskGroupService.findById(id);
        group.setName(dto.getName());
        group.setBoard(boardService.findById(dto.getBoardId()));

        TaskGroup updated = taskGroupService.update(id, group);
        return ResponseEntity.ok(new TaskGroupDTO(updated));
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            taskGroupService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }