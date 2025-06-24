package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBoardWithValidData() {
        // Arrange
        Board board = new Board();
        board.setBoardName("Projeto Final");
        board.setBoardDescription("Descrição do projeto");

        when(boardRepository.save(any(Board.class))).thenReturn(board);

        // Act
        Board savedBoard = boardService.createBoard(board);

        // Assert
        assertNotNull(savedBoard);
        assertEquals("Projeto Final", savedBoard.getBoardName());
        assertEquals("Descrição do projeto", savedBoard.getBoardDescription());

        verify(boardRepository, times(1)).save(board);
    }

    @Test
    void testGetBoardByIdWhenExists() {
        // Arrange
        Board board = new Board();
        board.setBoardId(1L);
        board.setBoardName("Projeto");

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        // Act
        Board found = boardService.getBoardById(1L);

        // Assert
        assertNotNull(found);
        assertEquals(1L, found.getBoardId());
        assertEquals("Projeto", found.getBoardName());
    }

    @Test
    void testGetBoardByIdWhenNotExists() {
        when(boardRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> boardService.getBoardById(99L)
        );

        assertEquals("Board não encontrado", exception.getMessage());
    }
}
