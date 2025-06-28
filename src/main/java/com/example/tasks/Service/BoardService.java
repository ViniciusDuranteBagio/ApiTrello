package com.example.tasks.service;

import com.example.tasks.dto.BoardDto;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board create(BoardDto dto) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setDescription(dto.getDescription());
        return boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Optional<Board> update(Long id, BoardDto dto) {
        return boardRepository.findById(id).map(board -> {
            board.setName(dto.getName());
            board.setDescription(dto.getDescription());
            return boardRepository.save(board);
        });
    }

    public boolean delete(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
