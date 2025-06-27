package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> create(@Valid @RequestBody Board board) {
        return new ResponseEntity<>(boardService.create(board), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Board> all() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public Board one(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/{id}")
    public Board update(@PathVariable Long id, @Valid @RequestBody Board board) {
        return boardService.update(id, board);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}