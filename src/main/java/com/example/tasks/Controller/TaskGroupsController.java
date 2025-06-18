package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroups;
import com.example.tasks.Service.TaskGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/taskGroups")
public class TaskGroupsController {
    @Autowired
    private final TaskGroupsService taskGroupsService;

    //Constructor
    public TaskGroupsController(TaskGroupsService taskGroupsService) {
        this.taskGroupsService = taskGroupsService;
    }

    //Gets
    @GetMapping("/buscar/id")
    public Optional<TaskGroups> buscarPorId(@RequestParam("id") Long id) {
        return taskGroupsService.buscarPorId(id);
    }
    @GetMapping("/buscar/nome")
    public List<TaskGroups> buscarPorNome(@RequestParam("nome") String nome){
        return taskGroupsService.buscarPorNome(nome);
    }
    @GetMapping("buscar/board/nome")
    public List<TaskGroups> buscarPorBoardNome(@RequestParam("nome") String nome){
        return taskGroupsService.buscarPorBoardNome(nome);
    }
    @GetMapping("buscar/board/id")
    public List<TaskGroups> buscarPorBoardId(@RequestParam("id") Long id){
        return taskGroupsService.buscarPorBoardId(id);
    }

    //Post
    @PostMapping("/salvar")
    public TaskGroups salvar(@RequestBody TaskGroups taskGroups){
        return taskGroupsService.salvar(taskGroups);
    }

    //Delete
    @DeleteMapping("deletar/id")
    public void deletar(@RequestParam("id")Long id) {
        taskGroupsService.deletar(id);
    }
}
