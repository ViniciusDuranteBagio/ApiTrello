package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/boards")

public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public Board postBoards(@Valid @RequestBody Board board){
        return boardService.saveBoard(board);
    }

    @GetMapping("/all")
    public List<Board> getAllBoards(){
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Optional<Board> getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        Board updated = boardService.updateBoard(id, updatedBoard);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.noContent().build();
    }
}

