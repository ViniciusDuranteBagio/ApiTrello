package com.example.tasks;

import com.example.tasks.Dto.TaskDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long taskGroupId;

    @BeforeEach
    void setUp() {

        Board board = new Board();
        board.setName("Quadro de Teste");
        board = boardRepository.save(board);
        if (board.getId() == null) {
            throw new RuntimeException("Falha ao salvar Board");
        }

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName("Grupo de Teste");
        taskGroup.setBoard(board);
        taskGroup = taskGroupRepository.save(taskGroup);
        if (taskGroup.getId() == null) {
            throw new RuntimeException("Falha ao salvar TaskGroup");
        }
    }

    @Test
    void criarTask_endpointDeveRetornarOk() throws Exception {
        TaskDto dto = new TaskDto();
        dto.setTitle("Task Integração");
        dto.setDescription("Descrição");
        dto.setStatus("TODO");
        dto.setTaskGroupId(1L);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}