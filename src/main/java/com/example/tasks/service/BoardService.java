package com.example.tasks.service;

import com.example.tasks.dto.Dtos;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Dtos.BoardResponse createBoard(Dtos.BoardRequest request) {
        Board board = new Board();
        board.setName(request.name());
        board.setDescription(request.description());
        Board savedBoard = boardRepository.save(board);
        return toBoardResponse(savedBoard);
    }

    @Transactional(readOnly = true)
    public List<Dtos.BoardResponse> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::toBoardResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Dtos.BoardResponse getBoardById(Long id) {
        Board board = findBoardById(id);
        return toBoardResponse(board);
    }

    @Transactional
    public Dtos.BoardResponse updateBoard(Long id, Dtos.BoardRequest request) {
        Board board = findBoardById(id);
        board.setName(request.name());
        board.setDescription(request.description());
        Board updatedBoard = boardRepository.save(board);
        return toBoardResponse(updatedBoard);
    }

    @Transactional
    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFoundException("Board não encontrado com o id: " + id);
        }
        boardRepository.deleteById(id);
    }

    // Método auxiliar para encontrar Board ou lançar exceção
    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado com o id: " + id));
    }

    // Método auxiliar para conversão de Entidade para DTO
    private Dtos.BoardResponse toBoardResponse(Board board) {
        return new Dtos.BoardResponse(board.getId(), board.getName(), board.getDescription());
    }
}
