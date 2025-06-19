package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board){ // Método para criar um novo board
        if (board.getBoardName().isEmpty() || board.getBoardName().length() < 3){
            throw new IllegalArgumentException("O nome não pode ter menos de 3 caracteres");
        }
        if (board.getBoardId() == null || board.getBoardId() < 0) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou negativo");
        }
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() { // List<Board> para retornar todos os boards
        return boardRepository.findAll();
    }

    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Quadro não encontrado com o ID: " + boardId));
    }

    public Board updateBoard(Long boardId, Board updatedBoard) {
        Board existingBoard = getBoardById(boardId);

        if (updatedBoard.getBoardName() != null && !updatedBoard.getBoardName().isEmpty()) { // Verifica se o nome do board foi atualizado
            existingBoard.setBoardName(updatedBoard.getBoardName()); // Atualiza o nome do board
        }
        if (updatedBoard.getBoardDescription() != null) { // Verifica se a descrição do board foi atualizada
            existingBoard.setBoardDescription(updatedBoard.getBoardDescription()); // Atualiza a descrição do board
        }

        return boardRepository.save(existingBoard);
    }

    public void deleteBoard(Long boardId) { // Método para deletar um board
        Board existingBoard = getBoardById(boardId);
        boardRepository.delete(existingBoard);  // Deleta o board existente
    }

}
