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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return boardService.buscarPorId(id).map(board -> ResponseEntity.ok(board))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarBoard(@PathVariable Long id, @Valid @RequestBody BoardDto boardDto){
        return boardService.atualizarBoard(id, boardDto)
                .map(boardAtualizado -> ResponseEntity.ok(boardAtualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarBoard(@PathVariable Long id){
        boolean deletado = boardService.deletarBoard(id);
        if(deletado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
