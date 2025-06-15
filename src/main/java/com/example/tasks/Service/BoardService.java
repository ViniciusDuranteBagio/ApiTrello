package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board não encontrado"));
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        Board existing = getBoardById(id);
        existing.setName(updatedBoard.getName());
        existing.setDescription(updatedBoard.getDescription());
        return boardRepository.save(existing);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
