package com.example.tasks.controller;

import com.example.tasks.Controller.TaskController;
import com.example.tasks.Model.Task;
import com.example.tasks.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void testCreateTask_Success() throws Exception {
        // Arrange
        Task task = new Task();
        task.setTitle("Test Task");
        task.setStatus(Task.Status.TODO);

        when(taskService.createTask(anyLong(), any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                .param("taskGroupId", "1")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"title\":\"Test Task\", \"status\":\"TODO\"}"))
                .andExpect(status().isOk());
    }
}
