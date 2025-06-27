package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping
    public List<Board> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Board getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Board create(@Valid @RequestBody Board board) {
        return service.create(board);
    }

    @PutMapping("/{id}")
    public Board update(@PathVariable Long id, @Valid @RequestBody Board board) {
        return service.update(id, board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}