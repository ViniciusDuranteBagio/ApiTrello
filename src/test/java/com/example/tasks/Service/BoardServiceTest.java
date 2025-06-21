package com.example.tasks.Service;

import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.DTO.BoardDTO;
import com.example.tasks.Exception.ResourceNotFoundException;
import com.example.tasks.Model.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    void createBoard_ShouldReturnSavedBoard() {
        BoardDTO input = new BoardDTO(null, "Projeto Final", "Descrição");
        Board savedBoard = new Board();
        savedBoard.setId(1L);
        savedBoard.setName("Projeto Final");
        savedBoard.setDescription("Descrição");

        when(boardRepository.save(any(Board.class))).thenReturn(savedBoard);

        BoardDTO result = boardService.createBoard(input);

        assertNotNull(result.id());
        assertEquals("Projeto Final", result.name());
        assertEquals("Descrição", result.description());
    }

    @Test
    void getBoardById_ShouldThrowExceptionWhenNotFound() {
        when(boardRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> boardService.getBoardById(1L));
    }
}