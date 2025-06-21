package com.example.tasks.Controller;

import com.example.tasks.Repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setup() {
        boardRepository.deleteAll();
    }

    @Test
    void createBoard_ShouldReturnCreatedStatus() throws Exception {
        String boardJson = "{\"name\":\"Novo Projeto\",\"description\":\"Descrição\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(boardJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Novo Projeto"));
    }
}