// src/test/java/com/example/tasks/controller/TarefaControllerTest.java
package com.example.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TarefaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarTituloFormatado() throws Exception {
        mockMvc.perform(get("/tarefas/titulo").param("titulo", "Estudar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tarefa: Estudar"));
    }
}
