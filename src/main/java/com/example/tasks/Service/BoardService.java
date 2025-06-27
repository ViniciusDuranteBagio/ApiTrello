package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService{

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board create(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> getById(Long id) {
        return boardRepository.findById(id);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Board update(Long id, Board updatedBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado"));

        board.setName(updatedBoard.getName());
        board.setDescription(updatedBoard.getDescription());

        return boardRepository.save(board);
    }
}
