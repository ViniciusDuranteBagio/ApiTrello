package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    //Constructor
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //Gets
    @GetMapping("/buscar/id")
    public Optional<Board> buscarPorId(@RequestParam("id") Long id) {
        return boardService.buscarPorId(id);
    }
    @GetMapping("/buscar/nome")
    public List<Board> buscarPorNome(@RequestParam("nome") String nome){
        return boardService.buscarPorNome(nome);
    }
    //Post
    @PostMapping("/salvar")
    public Board salvar(@RequestBody Board board){
        return boardService.salvar(board);
    }

    // Delete
    @DeleteMapping("deletar/id")
    public void deletar(@RequestParam("id")Long id) {
        boardService.deletar(id);
    }
}
