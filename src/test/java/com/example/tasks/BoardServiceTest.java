package com.example.tasks;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BoardServiceTest {

    private BoardRepository boardRepository;
    private BoardService boardService;

    @BeforeEach
    public void setUp() {
        boardRepository = mock(BoardRepository.class);
        boardService = new BoardService(boardRepository);
    }

    @Test
    public void testSaveBoard() {
        Board board = new Board();
        board.setName("Novo Board");

        when(boardRepository.save(board)).thenReturn(board);

        Board result = boardService.saveBoard(board);

        assertNotNull(result);
        assertEquals("Novo Board", result.getName());
    }

    @Test
    public void testFindAllBoards() {
        Board board1 = new Board();
        board1.setName("Board 1");

        Board board2 = new Board();
        board2.setName("Board 2");

        when(boardRepository.findAll()).thenReturn(Arrays.asList(board1, board2));

        List<Board> result = boardService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Board board = new Board();
        board.setId(1L);
        board.setName("Board Existente");

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        Board result = boardService.findById(1L);

        assertNotNull(result);
        assertEquals("Board Existente", result.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        when(boardRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> boardService.findById(1L));
    }

    @Test
    public void testUpdate() {
        Board existing = new Board();
        existing.setId(1L);
        existing.setName("Antigo");
        existing.setDescription("Descrição antiga");

        Board updated = new Board();
        updated.setName("Novo");
        updated.setDescription("Nova descrição");

        when(boardRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(boardRepository.save(existing)).thenReturn(existing);

        Board result = boardService.update(1L, updated);

        assertEquals("Novo", result.getName());
        assertEquals("Nova descrição", result.getDescription());
    }

    @Test
    public void testDelete() {
        Board board = new Board();
        board.setId(1L);

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        boardService.delete(1L);

        verify(boardRepository).delete(board);
    }
}

