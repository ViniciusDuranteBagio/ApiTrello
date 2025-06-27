package com.example.tasks.Service;


import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Model.TaskStatus;
import com.example.tasks.Repository.TaskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*TEM UM ERRO QUANDO EXECUTA O TESTE, DIZENDO QUE NÃO PODE SER INJETADO O MOCKITO
POREM ESTA FUNCIONANDO O TESTE
*/

public class TaskServiceTest {

    @Test
    void returnTasksById(){
        TaskRepository taskRepo = mock(TaskRepository.class);
        TaskService service = new TaskService(taskRepo);

        TaskGroup group = new TaskGroup();
        group.setTaskGroupId(1L);
        group.setTaskGroupName("Test Group");

        Task task = new Task();
        task.setTaskId(1L);
        task.setTaskTitle("Test Task");
        task.setTaskStatus(TaskStatus.TODO);
        task.setTaskGroup(group);

        when(taskRepo.findById(1L)).thenReturn(java.util.Optional.of(task));

        Task result = service.getTaskById(1L);

        assertNotNull(result);
        assertEquals("Test Task", result.getTaskTitle());
        assertEquals(TaskStatus.TODO, result.getTaskStatus());
        assertEquals("Test Group", result.getTaskGroup().getTaskGroupName());
    }
}

