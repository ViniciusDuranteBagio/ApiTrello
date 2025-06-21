package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito para este teste
class BoardServiceTest {

    @Mock // Cria um "mock" (simulação) do BoardRepository
    private BoardRepository boardRepository;

    @InjectMocks // Cria uma instância do BoardService e injeta o mock acima nele
    private BoardService boardService;

    @Test // Anotação que marca este método como um caso de teste
    @DisplayName("Deve salvar um Board com sucesso") // Descrição do teste
    void deveSalvarUmBoardComSucesso() {
        // Arrange (Organização): Preparamos o cenário do teste.
        // 1. Criamos um objeto board para ser o "input" do nosso serviço.
        Board boardParaSalvar = new Board();
        boardParaSalvar.setName("Board de Teste");

        // 2. Criamos o objeto que esperamos como "output".
        Board boardSalvo = new Board();
        boardSalvo.setId(1L);
        boardSalvo.setName("Board de Teste");

        // 3. Ensinamos ao mock o que fazer: "QUANDO (when) o método 'save' do repositório
        // for chamado com QUALQUER (any) objeto Board, ENTÃO (thenReturn) retorne o 'boardSalvo'".
        when(boardRepository.save(any(Board.class))).thenReturn(boardSalvo);

        // Act (Ação): Executamos o método que queremos testar.
        Board resultado = boardService.saveBoard(boardParaSalvar);

        // Assert (Verificação): Verificamos se o resultado foi o esperado.
        assertNotNull(resultado); // O resultado não pode ser nulo.
        assertEquals(boardSalvo.getId(), resultado.getId()); // O ID do resultado deve ser igual ao do board salvo.
        assertEquals(boardSalvo.getName(), resultado.getName()); // O nome deve ser o mesmo.
    }
}