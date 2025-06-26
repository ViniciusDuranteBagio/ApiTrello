package com.example.tasks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.tasks.dto.Dtos;
import com.example.tasks.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest foca nos testes da camada web (Controller), sem carregar o contexto completo
@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // @MockBean cria um mock do Service no contexto do Spring
    @MockBean
    private BoardService boardService;

    @Test
    void deveCriarBoardERetornarStatus201() throws Exception {
        // Arrange
        var request = new Dtos.BoardRequest("Projeto Final API", "Descrição do projeto.");
        var response = new Dtos.BoardResponse(1L, "Projeto Final API", "Descrição do projeto.");

        when(boardService.createBoard(any(Dtos.BoardRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Projeto Final API"));
    }
}
