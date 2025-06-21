package com.example.tasks.Controller;

import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.DTO.TaskGroupDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-groups")
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskGroupDTO createTaskGroup(@RequestBody TaskGroupDTO taskGroupDTO) {
        return taskGroupService.createTaskGroup(taskGroupDTO);
    }

    @GetMapping("/board/{boardId}")
    public List<TaskGroupDTO> getTaskGroupsByBoard(@PathVariable Long boardId) {
        return taskGroupService.getTaskGroupsByBoard(boardId);
    }

    @PutMapping("/{id}")
    public TaskGroupDTO updateTaskGroup(@PathVariable Long id, @RequestBody TaskGroupDTO taskGroupDTO) {
        return taskGroupService.updateTaskGroup(id, taskGroupDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskGroup(@PathVariable Long id) {
        taskGroupService.deleteTaskGroup(id);
    }
}