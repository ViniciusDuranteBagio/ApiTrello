package com.example.tasks.Controller;

import com.example.tasks.Service.BoardService;
import com.example.tasks.DTO.BoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDTO createBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.createBoard(boardDTO);
    }

    @GetMapping
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PutMapping("/{id}")
    public BoardDTO updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoard(id, boardDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}