package com.example.tasks.Controller;

import com.example.tasks.Dtos.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> criarBoard(@Valid @RequestBody BoardDto boardDto){
        Board boardCriado = boardService.criarBoard(boardDto);
        return ResponseEntity.ok(boardCriado);
    }

    @GetMapping
    public ResponseEntity<List<Board>> listarTodos(){
        List<Board> boards = boardService.listarTodos();
        return ResponseEntity.ok(boards);
    }
}
