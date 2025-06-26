package com.example.tasks;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Board board;

    @BeforeEach
    public void setUp() {
        taskGroupRepository.deleteAll();
        boardRepository.deleteAll();

        board = new Board();
        board.setName("Quadro Principal");
        board = boardRepository.save(board);
    }

    @Test
    public void testCreateTaskGroup_statusOkAndHasContent() throws Exception {
        TaskGroup group = new TaskGroup();
        group.setName("Sprint 1");
        group.setBoard(board);

        MvcResult result = mockMvc.perform(post("/task-groups/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(group)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        System.out.println("Resposta JSON: " + jsonResponse);
    }
}
