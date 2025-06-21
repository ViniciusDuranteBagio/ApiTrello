package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) { // Injeção de dependência
        this.boardService = boardService;
    }

    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id){
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public Board uptadeBoard(@PathVariable Long id, @RequestBody Board updatedBoard){
        return boardService.updateBoard(id, updatedBoard);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id){
        boardService.deleteBoard(id);
    }
}
