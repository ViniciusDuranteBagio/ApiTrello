package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board updateBoard(Long id, Board newData) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        board.updateBoard(newData);
        return boardRepository.save(board);
    }

    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }
}
