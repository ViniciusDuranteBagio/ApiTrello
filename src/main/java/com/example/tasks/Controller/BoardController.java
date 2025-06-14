package com.example.tasks.Controller;

import com.example.tasks.Dtos.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
