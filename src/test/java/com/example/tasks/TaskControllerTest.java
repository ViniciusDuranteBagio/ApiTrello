package com.example.tasks;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskGroup group;

    @BeforeEach
    public void setup() {
        taskRepository.deleteAll();
        taskGroupRepository.deleteAll();
        boardRepository.deleteAll();

        Board board = new Board();
        board.setName("Quadro Teste");
        board = boardRepository.save(board);

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName("Grupo Teste");
        taskGroup.setBoard(board);
        group = taskGroupRepository.save(taskGroup);
    }

    @Test
    public void testCreateTask_statusOkAndJson() throws Exception {
        Task task = new Task();
        task.setTitle("Nova Tarefa");
        task.setDescription("Descrição qualquer");
        task.setStatus(Task.Status.TODO);
        task.setTaskGroup(group);

        mockMvc.perform(post("/tasks/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}

