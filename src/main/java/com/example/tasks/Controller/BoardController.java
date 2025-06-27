package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody @Valid Board board) {
        Board savedBoard = boardService.create(board);
        return ResponseEntity.status(201).body(savedBoard);
    }

    @GetMapping("/summary")
    public List<Map<String, Object>> getBoardSummaries() {
        List<Board> boards = boardService.getAll();

        List<Map<String, Object>> response = new ArrayList<>();

        for (Board board : boards) {
            Map<String, Object> boardData = new HashMap<>();
            boardData.put("name", board.getName());

            List<String> groupNames = board.getTaskGroup().stream()
                    .map(TaskGroup::getName)
                    .toList();

            boardData.put("taskGroups", groupNames);

            response.add(boardData);
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody @Valid Board board) {
        try {
            Board updated = boardService.update(id, board);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
