package com.example.tasks;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskStatus;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void deveSalvarTaskComSucesso() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Testar Serviço");
        task.setStatus(TaskStatus.TODO);

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task saved = taskService.saveTask(task);

        assertEquals("Testar Serviço", saved.getTitle());
        assertEquals(TaskStatus.TODO, saved.getStatus());
    }

    @Test
    public void deveBuscarTaskPorId() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Buscar Task");
        task.setStatus(TaskStatus.DONE);

        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertEquals(true, result.isPresent());
        assertEquals("Buscar Task", result.get().getTitle());
    }
}
