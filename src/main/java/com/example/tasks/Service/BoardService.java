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

    // Create a new board
    public Board createBoard(Board board) {
        if (board.getBoardName() == null || board.getBoardName().length() < 3) {
            throw new IllegalArgumentException("Board name must be at least 3 characters long");
        }
        return boardRepository.save(board);
    }

    // Get all boards from the database
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // Update the board
    public Board updateBoard(Long id, Board updatedBoard) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + id));

        if (updatedBoard.getBoardName() != null && !updatedBoard.getBoardName().isEmpty()) {
            existingBoard.setBoardName(updatedBoard.getBoardName());
        }

        if (updatedBoard.getBoardDescription() != null && !updatedBoard.getBoardDescription().isEmpty()){
            existingBoard.setBoardDescription(updatedBoard.getBoardDescription());
        }

        return boardRepository.save(existingBoard);

    }
    // Delete board
    public void deleteBoard (Long boardId) {
        if (!boardRepository.existsById(boardId)){
            throw new IllegalArgumentException("Board not found with id: " + boardId);
        }
        boardRepository.deleteById(boardId);
    }
}
