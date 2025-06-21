package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<Board> create(@Valid @RequestBody Board board) {
        return ResponseEntity.ok(boardService.saveBoard(board));
    }

    @GetMapping("/all")
    public List<Board> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> findById(@PathVariable Long id) {

        Board board = null;

        board = boardService.findById(id);

        if (board != null) {

            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @Valid @RequestBody Board board) {

        Board _board = boardService.findById(id);

        if (_board != null) {
            _board.setName(board.getName());
            _board.setDescription(board.getDescription());

            return ResponseEntity.ok(boardService.saveBoard(_board));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {

        Board board = null;

        board = boardService.findById(id);

        if (board != null) {
            boardService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Board removido.");
            return ResponseEntity.status(200).body(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Board não encontrado.");
            return ResponseEntity.status(400).body(response);
        }
    }
}

