package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskGroupRepository taskGroupRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskGroupRepository = mock(TaskGroupRepository.class);
        taskService = new TaskService(taskRepository, taskGroupRepository);
    }

    @Test
    void testCreateTaskWithValidData() {
        TaskGroup group = new TaskGroup();
        group.setTaskGroupId(1L); // ESSENCIAL para que o findById(1L) funcione

        Task task = new Task();
        task.setTaskTitle("Minha tarefa");
        task.setTaskStatus(Task.taskStatus.valueOf("TODO"));
        task.setTaskGroup(group); // O Task precisa do grupo com ID

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group)); // Isso só funciona se o ID for 1L
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task savedTask = taskService.createTask(task);

        assertNotNull(savedTask);
        assertEquals("Minha tarefa", savedTask.getTaskTitle());
    }


    @Test
    void testCreateTaskWithInvalidTitle() {
        Task task = new Task();
        task.setTaskTitle("ok");
        task.setTaskStatus(Task.taskStatus.valueOf("TODO"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(task);
        });

        assertEquals("O nome não pode ser vazio ou ter menos que 3 caracteres.", exception.getMessage());
    }

    @Test
    void testCreateTaskWithNullStatus() {
        Task task = new Task();
        task.setTaskTitle("Tarefa válida");
        task.setTaskStatus(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(task);
        });

        assertEquals("O status não pode ser nulo.", exception.getMessage());
    }
}
