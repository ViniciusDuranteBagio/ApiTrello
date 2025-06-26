package com.example.tasks.service;

import com.example.tasks.dto.BoardDTO;
import com.example.tasks.model.Board;
import com.example.tasks.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        BeanUtils.copyProperties(boardDTO, board, "id");
        Board savedBoard = boardRepository.save(board);
        return convertToDto(savedBoard);
    }

    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado com o id: " + id));
        return convertToDto(board);
    }

    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado com o id: " + id));

        BeanUtils.copyProperties(boardDTO, existingBoard, "id");
        Board updatedBoard = boardRepository.save(existingBoard);
        return convertToDto(updatedBoard);
    }

    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFoundException("Board não encontrado com o id: " + id);
        }
        boardRepository.deleteById(id);
    }

    private BoardDTO convertToDto(Board board) {
        BoardDTO dto = new BoardDTO();
        BeanUtils.copyProperties(board, dto);
        return dto;
    }
}