package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardServiceTest {

    @Test
    public void boardServiceTest() {
        BoardRepository repo = Mockito.mock(BoardRepository.class);
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(new Board(1L, "Board Teste", "desc", null)));
        BoardService service = new BoardService(repo);

        Board board = service.getBoardById(1L);

        assertNotNull(board);
        assertEquals("Board Teste", board.getBoardName());

        System.out.println(board.getBoardName());
    }
}