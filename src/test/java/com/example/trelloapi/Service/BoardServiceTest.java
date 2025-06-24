package com.example.trelloapi.Service;

import com.example.trelloapi.Model.Board;
import com.example.trelloapi.Repository.BoardRepository;
import com.example.trelloapi.Service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// A anotação @ExtendWith é a forma mais moderna de inicializar os mocks do Mockito com JUnit 5.
// Ela substitui a necessidade de usar MockitoAnnotations.openMocks(this) em um método @BeforeEach.
@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private BoardService boardService;

    @Test
    @DisplayName("Deve salvar um Board com sucesso quando o nome for válido")
    void testSaveBoardSuccess() {
        // Arrange
        Board boardParaSalvar = new Board();
        boardParaSalvar.setName("Test Board");
        boardParaSalvar.setDescription("Description");

        when(boardRepository.save(any(Board.class))).thenReturn(boardParaSalvar);

        // Act
        Board boardSalvo = boardService.save(boardParaSalvar);

        // Assert
        assertNotNull(boardSalvo);
        assertEquals("Test Board", boardSalvo.getName());
        // Verifica se o método save do repositório foi chamado exatamente 1 vez.
        verify(boardRepository, times(1)).save(boardParaSalvar);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um Board com nome muito curto")
    void testSaveBoardNameTooShort() {
        // Arrange
        Board board = new Board();
        board.setName("ab"); // Nome inválido

        // Act & Assert
        // Verifica se uma IllegalArgumentException é lançada
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boardService.save(board);
        });

        String expectedMessage = "Nome do Board deve conter no mínimo 3 caracteres.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

        // Verifica que, como a validação falhou, o método save NUNCA foi chamado.
        verify(boardRepository, never()).save(any(Board.class));
    }

    @Test
    @DisplayName("Deve atualizar um Board com sucesso quando ele existe")
    void testUpdateBoardSuccess() {
        // Arrange
        Long boardId = 1L;
        Board boardExistente = new Board();
        boardExistente.setId(boardId);
        boardExistente.setName("Old Name");
        boardExistente.setDescription("Old Description");

        Board boardComNovosDados = new Board();
        boardComNovosDados.setName("New Name");
        boardComNovosDados.setDescription("New Description");

        // Configura o mock para encontrar o board existente.
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(boardExistente));

        // Usa um ArgumentCaptor para "capturar" o objeto que é passado para o método save.
        // Isso nos permite verificar se o objeto foi modificado corretamente antes de ser salvo.
        ArgumentCaptor<Board> boardCaptor = ArgumentCaptor.forClass(Board.class);

        // Quando save for chamado, retorne o próprio objeto que foi passado para ele.
        when(boardRepository.save(any(Board.class))).thenAnswer(invocation -> invocation.getArgument(0));


        // Act
        boardService.update(boardId, boardComNovosDados);

        // Assert
        // 1. Verifica se o método save foi chamado.
        verify(boardRepository, times(1)).save(boardCaptor.capture());

        // 2. Pega o objeto que foi capturado.
        Board boardCapturado = boardCaptor.getValue();

        // 3. Verifica se os dados do objeto capturado foram realmente atualizados.
        assertEquals(boardId, boardCapturado.getId()); // Garante que o ID não mudou
        assertEquals("New Name", boardCapturado.getName());
        assertEquals("New Description", boardCapturado.getDescription());

        // 4. Garante que o método findById também foi chamado.
        verify(boardRepository, times(1)).findById(boardId);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar um Board que não existe")
    void testUpdateBoardNotFound() {
        // Arrange
        Long boardId = 1L;
        Board boardDetails = new Board();
        boardDetails.setName("New Name");

        // Simula que o board não foi encontrado no banco de dados.
        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            boardService.update(boardId, boardDetails);
        });

        assertEquals("Board não encontrado com ID: " + boardId, exception.getMessage());

        // Garante que findById foi chamado, mas save não.
        verify(boardRepository, times(1)).findById(boardId);
        verify(boardRepository, never()).save(any(Board.class));
    }
}