package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    public void testCreateBoard() {
        Board board = new Board();
        board.setName("Projeto X");
        board.setDescription("Descricao do projeto");

        Mockito.when(boardRepository.save(any(Board.class))).thenReturn(board);

        Board created = boardService.create(new Board());

        Assertions.assertNotNull(created);
        Assertions.assertEquals("Projeto X", created.getName());
        Mockito.verify(boardRepository, Mockito.times(1)).save(any(Board.class));
    }
}
