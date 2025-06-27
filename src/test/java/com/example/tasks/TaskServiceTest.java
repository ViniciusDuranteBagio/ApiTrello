package com.example.tasks;

import com.example.tasks.Dto.TaskDto;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.Service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskGroupRepository = mock(TaskGroupRepository.class);
        taskService = new TaskService();
        taskService.taskRepository = taskRepository;
        taskService.taskGroupRepository = taskGroupRepository;
    }

    @Test
    void criarTask_deveCriarTaskComSucesso() {
        TaskDto dto = new TaskDto();
        dto.setTitle("Nova Task");
        dto.setDescription("Descrição");
        dto.setStatus("TODO");
        dto.setTaskGroupId(1L);

        TaskGroup group = new TaskGroup();
        group.setId(1L);

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task task = taskService.criarTask(dto);

        assertEquals("Nova Task", task.getTitle());
        assertEquals(Task.Status.TODO, task.getStatus());
        assertEquals(group, task.getTaskGroup());
    }

    @Test
    void criarTask_deveLancarExcecaoQuandoStatusInvalido() {
        TaskDto dto = new TaskDto();
        dto.setTitle("Task");
        dto.setStatus("INVALIDO");
        dto.setTaskGroupId(1L);

        TaskGroup group = new TaskGroup();
        group.setId(1L);

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group));

        assertThrows(IllegalArgumentException.class, () -> taskService.criarTask(dto));
    }

    @Test
    void criarTask_deveLancarExcecaoQuandoTaskGroupNaoExiste() {
        TaskDto dto = new TaskDto();
        dto.setTitle("Task");
        dto.setStatus("TODO");
        dto.setTaskGroupId(99L);

        when(taskGroupRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taskService.criarTask(dto));
    }
}