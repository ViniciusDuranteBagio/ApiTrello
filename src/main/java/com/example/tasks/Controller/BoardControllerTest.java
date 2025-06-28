package com.example.tasks.controller;

//Não consegui rodar o programa por erros de versão no Java, mokito e jdk


import com.example.tasks.dto.BoardDto;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndReturnBoard() throws Exception {
        BoardDto dto = new BoardDto();
        dto.setName("Board de Teste");
        dto.setDescription("Descrição qualquer");

        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is("Board de Teste")));
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        mockMvc.perform(get("/boards/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListAllBoards() throws Exception {
        Board board = new Board();
        board.setName("Listar");
        board.setDescription("Teste");
        boardRepository.save(board);

        mockMvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }
}
