package com.example.tasks.controller;

import com.example.tasks.dto.Dtos;
import com.example.tasks.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Dtos.BoardResponse> createBoard(@Valid @RequestBody Dtos.BoardRequest request) {
        Dtos.BoardResponse response = boardService.createBoard(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Dtos.BoardResponse>> getAllBoards() {
        List<Dtos.BoardResponse> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dtos.BoardResponse> getBoardById(@PathVariable Long id) {
        Dtos.BoardResponse board = boardService.getBoardById(id);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dtos.BoardResponse> updateBoard(@PathVariable Long id, @Valid @RequestBody Dtos.BoardRequest request) {
        Dtos.BoardResponse updatedBoard = boardService.updateBoard(id, request);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
