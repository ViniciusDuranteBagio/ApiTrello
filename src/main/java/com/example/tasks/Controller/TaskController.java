package com.example.tasks.Controller;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.Task.Situacao;
import com.example.tasks.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    //Constructor
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Get
    @GetMapping("/buscar/id")
    public Optional<Task> buscarPorId(@RequestParam("id")Long id){
        return taskService.buscarPorId(id);
    }
    @GetMapping("/buscar/titulo")
    public List<Task> buscarPorTitulo(@RequestParam("titulo")String titulo){
        return taskService.buscarPorTaskTitulo(titulo);
    }
    @GetMapping("/buscar/situacao")
    public List<Task> buscarPorSituacao(@RequestParam("situacao")Situacao situacao){
        return taskService.buscarPorSituacao(situacao);
    }
    @GetMapping("/buscar/taskgroups/id")
    public List<Task> buscarPorTaskGroupsId(@RequestParam("id")Long id){
        return taskService.buscarPorTaskGroupsId(id);
    }
    @GetMapping("/buscar/taskgroups/nome")
    public List<Task> buscarPorTaskGroupsNome(@RequestParam("nome")String nome){
        return taskService.buscarPorTaskGroupsNome(nome);
    }

    //Post
    @PostMapping("/salvar")
    public Task salvar(@RequestBody Task task){
        return taskService.salvar(task);
    }

    // Delete
    @DeleteMapping("deletar/id")
    public void deletar(@RequestParam("id")Long id) {
        taskService.deletar(id);
    }
}
