package com.example.tasks.service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.Service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskGroupRepository taskGroupRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask_ValidInput_Success() {

       Long taskGroupId = 1L;
       Task task = new Task();
         task.setTitle("Test Task");
         task.setStatus(Task.Status.TODO);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setId(taskGroupId);

        when(taskGroupRepository.findById(taskGroupId)).thenReturn(Optional.of(taskGroup));
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(taskGroupId, task);

        assertEquals("Test Task", result.getTitle());
        assertEquals(Task.Status.TODO, result.getStatus());
        verify(taskGroupRepository, times(1)).findById(taskGroupId);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testCreateTask_MissingTitle_ThrowsException() {
        Long taskGroupId = 1L;
        Task task = new Task();
        task.setStatus(Task.Status.TODO);

        assertThrows(IllegalArgumentException.class, () -> taskService.createTask(taskGroupId, task));
        verify(taskGroupRepository, never()).findById(anyLong());
        verify(taskRepository, never()).save(any());
    }
}
