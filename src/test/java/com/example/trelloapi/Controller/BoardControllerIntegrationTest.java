package com.example.trelloapi.Controller;

import com.example.trelloapi.Model.Board;
import com.example.trelloapi.Repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        boardRepository.deleteAll();
    }

    @Test
    void testCreateBoard() throws Exception {
        Board board = new Board();
        board.setName("Test Board");
        board.setDescription("Description");

        mockMvc.perform(post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(board)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Board"));
    }

    @Test
    void testGetAllBoards() throws Exception {
        Board board1 = new Board(null, "Board 1", "Desc 1", null);
        Board board2 = new Board(null, "Board 2", "Desc 2", null);
        boardRepository.save(board1);
        boardRepository.save(board2);

        mockMvc.perform(get("/api/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Board 1"));
    }

    @Test
    void testGetBoardById() throws Exception {
        Board board = new Board(null, "Single Board", "Description", null);
        board = boardRepository.save(board);

        mockMvc.perform(get("/api/boards/" + board.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Single Board"));
    }

    @Test
    void testUpdateBoard() throws Exception {
        Board board = new Board(null, "Old Name", "Old Description", null);
        board = boardRepository.save(board);

        Board updatedBoard = new Board(null, "Updated Name", "Updated Description", null);

        mockMvc.perform(put("/api/boards/" + board.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBoard)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    void testDeleteBoard() throws Exception {
        Board board = new Board(null, "Board to Delete", "Description", null);
        board = boardRepository.save(board);

        mockMvc.perform(delete("/api/boards/" + board.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/boards/" + board.getId()))
                .andExpect(status().isNotFound());
    }
}
