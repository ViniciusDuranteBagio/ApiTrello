package com.example.trelloapi.Service;

import com.example.trelloapi.Model.Board;
import com.example.trelloapi.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Board save(Board board) {
        if (board.getName() == null || board.getName().length() < 3) {
            throw new IllegalArgumentException("Nome do Board deve conter no mínimo 3 caracteres.");
        }
        return boardRepository.save(board);
    }

    public Board update(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado com ID: " + id));
        
        if (boardDetails.getName() == null || boardDetails.getName().length() < 3) {
            throw new IllegalArgumentException("Nome do Board deve conter no mínimo 3 caracteres.");
        }
        
        board.setName(boardDetails.getName());
        board.setDescription(boardDetails.getDescription());
        return boardRepository.save(board);
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
