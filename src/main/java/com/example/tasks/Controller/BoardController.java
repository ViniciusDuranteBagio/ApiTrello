package com.example.tasks.Controller;

import com.example.tasks.Dto.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> criarBoard(@RequestBody BoardDto boardDto) {
        Board createdBoard = boardService.criarBoard(boardDto);
        return ResponseEntity.ok(createdBoard);
    }

    @GetMapping
    public ResponseEntity<List<Board>> listarBoards() {
        return ResponseEntity.ok(boardService.listarBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> atualizarBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        return ResponseEntity.ok(boardService.atualizarBoard(id, boardDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBoard(@PathVariable Long id) {
        boardService.deletarBoard(id);
        return ResponseEntity.noContent().build();
    }
}