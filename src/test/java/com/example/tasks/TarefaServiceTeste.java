package com.example.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TarefaServiceTeste {

	private final TarefaService tarefaService = new TarefaService();

	@Test
	public void deveFormatarTituloCorretamente() {
		String resultado = tarefaService.formatarTitulo("Estudar");
		assertEquals("Tarefa: Estudar", resultado);
	}
}
