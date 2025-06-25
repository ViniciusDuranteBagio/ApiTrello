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

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Optional<Board> updateBoard(Long id, Board updatedBoard) {
        return boardRepository.findById(id).map(board -> {
            board.setName(updatedBoard.getName());
            board.setDescription(updatedBoard.getDescription());
            return boardRepository.save(board);
        });
    }

    public boolean deleteBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

