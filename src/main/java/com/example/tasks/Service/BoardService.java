package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.DTO.BoardDTO;
import com.example.tasks.Exception.ResourceNotFoundException;
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
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setName(boardDTO.name());
        board.setDescription(boardDTO.description());
        Board savedBoard = boardRepository.save(board);
        return new BoardDTO(savedBoard.getId(), savedBoard.getName(), savedBoard.getDescription());
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(board -> new BoardDTO(board.getId(), board.getName(), board.getDescription()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board não encontrado"));
        return new BoardDTO(board.getId(), board.getName(), board.getDescription());
    }

    @Transactional
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board não encontrado"));
        board.setName(boardDTO.name());
        board.setDescription(boardDTO.description());
        Board updatedBoard = boardRepository.save(board);
        return new BoardDTO(updatedBoard.getId(), updatedBoard.getName(), updatedBoard.getDescription());
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board não encontrado"));
        boardRepository.delete(board);
    }
}