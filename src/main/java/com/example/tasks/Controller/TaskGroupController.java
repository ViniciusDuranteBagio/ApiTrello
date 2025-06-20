package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task_group")
public class TaskGroupController {

    private TaskGroupService taskGroupService;

   public TaskGroupController(TaskGroupService taskGroupService){this.taskGroupService = taskGroupService;}

    @PostMapping
    public TaskGroup createTaskGroup(@RequestBody TaskGroup taskGroup){return taskGroupService.createTaskGroup(taskGroup);}

    @GetMapping
    public List<TaskGroup> getAllTaskGroups(){return taskGroupService.getAllTaskGroups();}

    @GetMapping("/{id}")
    public TaskGroup getTaskGroupById(@PathVariable Long id){return taskGroupService.getTaskGroupById(id);}

    @PutMapping("/{id}")
    public TaskGroup updateTaskGroup(@PathVariable Long id, @RequestBody TaskGroup updatedTaskGroup){
       return taskGroupService.updateTaskGroup(id, updatedTaskGroup);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskGroup(@PathVariable Long id){ taskGroupService.deleteTaskGroup(id);}
}
