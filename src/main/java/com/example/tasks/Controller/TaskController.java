package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RestController
@RequestMapping("/api")  //Marca a URL
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/put/{id}")
    @ResponseBody
    public Task saveTask(@PathVariable Long id, @RequestBody Task task){
        Optional<Task> tarefadb = taskService.getTaskById(id);
        Task tarefa = tarefadb.orElseThrow(() -> new EntityNotFoundException("Entity with ID " + id + " not found"));;
        tarefa.setName(task.getName());
        tarefa.setDescription(task.getDescription());
        tarefa.setStatus(task.getStatus());
        return taskService.saveTask(tarefa);
    }

    @PostMapping
    @ResponseBody
    public Task toCreate(@RequestBody Task task){
        return taskService.create(task);
    }

    @DeleteMapping("/delec")
    public void delete(){
         taskService.deleteAll();
    }
}