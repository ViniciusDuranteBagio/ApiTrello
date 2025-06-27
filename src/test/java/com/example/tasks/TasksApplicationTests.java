package com.example.tasks;

import com.example.tasks.Controller.TaskController;
import com.example.tasks.Model.Board;
import com.example.tasks.Model.Status;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TasksApplicationTests {

	@Test
	void contextLoads() {
		Task task = new Task();
	}

	@Autowired
	private TaskController taskController;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskGroupRepository taskGroupRepository;

	@Autowired
	private BoardRepository boardRepository;

	@BeforeEach
	void limparBanco() {
		taskRepository.deleteAll();
		taskGroupRepository.deleteAll();
		boardRepository.deleteAll();
	}

	@Test
	void testCriarETrazerTodasAsTasks() {
		// Criar Board e Group para associar à Task
		Board board = new Board();
		board.setName("Quadro Teste");
		board.setDescription("Descrição");
		board = boardRepository.save(board);

		TaskGroup group = new TaskGroup();
		group.setName("Grupo 1");
		group.setBoard(board);
		group = taskGroupRepository.save(group);

		Task novaTask = new Task();
		novaTask.setTitle("Tarefa 1");
		novaTask.setDescription("Descrição da tarefa 1");
		novaTask.setStatus(Status.TODO);
		novaTask.setTaskGroup(group);

		taskController.postTasks(novaTask);

		List<Task> lista = taskController.getAllTasks();
		assertEquals(1, lista.size());
		assertEquals("Tarefa 1", lista.get(0).getTitle());
	}

	@Test
	void testBuscarTaskPorIdInexistente_deveLancarExcecao() {
		Optional<Task> taskOptional = taskController.getTaskById(123456L);
		assertTrue(taskOptional.isEmpty());
	}
}
