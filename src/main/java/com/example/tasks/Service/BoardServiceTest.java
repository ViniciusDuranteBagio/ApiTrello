package com.example.tasks.Service;

import com.example.tasks.dto.BoardDto;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateBoard() {
        BoardDto dto = new BoardDto();
        dto.setName("Projeto X");
        dto.setDescription("Teste Unitário");

        Board savedBoard = new Board(1L, dto.getName(), dto.getDescription(), null);

        when(boardRepository.save(any(Board.class))).thenReturn(savedBoard);

        Board result = boardService.create(dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Projeto X");
        verify(boardRepository, times(1)).save(any(Board.class));
    }

    @Test
    void shouldFindById() {
        Board board = new Board(1L, "Board Test", "Descrição", null);
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));

        Optional<Board> result = boardService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Board Test");
    }
}
