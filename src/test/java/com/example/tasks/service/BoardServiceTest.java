package com.example.tasks.service;

import com.example.tasks.dto.Dtos;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void deveCriarBoardComSucesso() {
        // Arrange
        var request = new Dtos.BoardRequest("Projeto Teste", "Descrição do projeto teste.");

        Board boardSalvo = new Board(1L, "Projeto Teste", "Descrição do projeto teste.", null);

        when(boardRepository.save(any(Board.class))).thenReturn(boardSalvo);

        // Act
        Dtos.BoardResponse response = boardService.createBoard(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Projeto Teste", response.name());
        assertEquals("Descrição do projeto teste.", response.description());
    }
}
