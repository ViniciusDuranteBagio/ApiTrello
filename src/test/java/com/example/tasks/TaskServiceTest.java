package com.example.tasks;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.Service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskGroupRepository taskGroupRepository;
    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskGroupRepository = mock(TaskGroupRepository.class);
        taskService = new TaskService(taskRepository, taskGroupRepository);
    }

    @Test
    public void testCreateTaskValidGroup() {
        TaskGroup group = new TaskGroup();
        group.setId(1L);

        Task task = new Task();
        task.setTitle("Nova tarefa");
        task.setTaskGroup(group);

        when(taskGroupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.create(task);

        assertNotNull(result);
        assertEquals("Nova tarefa", result.getTitle());
        verify(taskRepository).save(task);
    }

    @Test
    public void testCreateTaskInvalidGroup() {
        TaskGroup group = new TaskGroup();
        group.setId(999L);

        Task task = new Task();
        task.setTaskGroup(group);

        when(taskGroupRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taskService.create(task));
    }

    @Test
    public void testFindById() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Buscar tarefa");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.findById(1L);

        assertEquals("Buscar tarefa", result.getTitle());
    }

    @Test
    public void testFindByIdNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> taskService.findById(1L));
    }

    @Test
    public void testFindAll() {
        Task task1 = new Task();
        task1.setTitle("Tarefa 1");

        Task task2 = new Task();
        task2.setTitle("Tarefa 2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<Task> result = taskService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testUpdate() {
        Task existing = new Task();
        existing.setId(1L);
        existing.setTitle("Antiga");
        existing.setDescription("desc");
        existing.setStatus(Task.Status.TODO);

        Task updated = new Task();
        updated.setTitle("Nova");
        updated.setDescription("atualizada");
        updated.setStatus(Task.Status.DONE);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(taskRepository.save(existing)).thenReturn(existing);

        Task result = taskService.update(1L, updated);

        assertEquals("Nova", result.getTitle());
        assertEquals("atualizada", result.getDescription());
        assertEquals(Task.Status.DONE, result.getStatus());
    }

    @Test
    public void testDelete() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.delete(1L);

        verify(taskRepository).delete(task);
    }
}

