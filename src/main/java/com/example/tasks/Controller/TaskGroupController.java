package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupController(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    @PostMapping
    public ResponseEntity<TaskGroup> create(@RequestBody @Valid TaskGroup taskGroup) {
        if (taskGroup.getBoard() == null || taskGroup.getBoard().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        return boardRepository.findById(taskGroup.getBoard().getId())
                .map(board -> {
                    taskGroup.setBoard(board);
                    TaskGroup savedTaskGroup = taskGroupRepository.save(taskGroup);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskGroup);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TaskGroup> getAll() {
        return taskGroupRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getById(@PathVariable Long id) {
        return taskGroupRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable Long id, @RequestBody @Valid TaskGroup taskGroup) {
        return taskGroupRepository.findById(id)
                .map(existingTaskGroup -> {
                    taskGroup.setId(id);
                    if (taskGroup.getBoard() == null) {
                        taskGroup.setBoard(existingTaskGroup.getBoard());
                    }
                    TaskGroup updatedTaskGroup = taskGroupRepository.save(taskGroup);
                    return ResponseEntity.ok(updatedTaskGroup);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (taskGroupRepository.existsById(id)) {
            taskGroupRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/board/{boardId}")
    public List<TaskGroup> getByBoardId(@PathVariable Long boardId) {
        return taskGroupRepository.findByBoardId(boardId);
    }
}
