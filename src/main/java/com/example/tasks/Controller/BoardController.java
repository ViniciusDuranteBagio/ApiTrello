package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardRepository repository;

    public BoardController(BoardRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Board> create(@RequestBody @Valid Board board) {
        Board savedBoard = repository.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }

    @GetMapping
    public List<Board> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody @Valid Board board) {
        return repository.findById(id)
                .map(existingBoard -> {
                    board.setId(id);
                    Board updatedBoard = repository.save(board);
                    return ResponseEntity.ok(updatedBoard);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

