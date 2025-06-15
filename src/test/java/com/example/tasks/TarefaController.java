// src/main/java/com/example/tasks/controller/TarefaController.java
package com.example.tasks;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @GetMapping("/titulo")
    public String getTitulo(@RequestParam String titulo) {
        return "Tarefa: " + titulo;
    }
}
