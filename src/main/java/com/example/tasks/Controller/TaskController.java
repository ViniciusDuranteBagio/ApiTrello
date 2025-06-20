package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService){this.taskService = taskService;}

    @PostMapping
    public Task createTask( @RequestBody Long groupId, Task task){
        return taskService.createTask(groupId, task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
       return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }


    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updateTask){
        return taskService.updateTask(id, updateTask);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task com id: " + id + " deletada com sucesso";
    }


}
