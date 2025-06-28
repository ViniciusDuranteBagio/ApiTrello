package com.example.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarefaServiceTest {

    private final TarefaService tarefaService = new TarefaService();

    @Test
    public void deveFormatarTituloCorretamente() {
        String resultado = tarefaService.formatarTitulo("Estudar");
        assertEquals("Tarefa do Trello: Estudar", resultado);
    }
}
