package com.example.tasks.Controller;

import com.example.tasks.Model.Board;
import com.example.tasks.Service.BoardSer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")

public class BoardCon {

    private BoardSer boardSer;

    public BoardCon(BoardSer boardSer){
        this.boardSer = boardSer;
    }

    @PostMapping
    public Board crearBoard(@RequestBody Board board){
        return boardSer.criarBoard(board);
    }

    @GetMapping
    public List<Board> pegarBoards(){
        return boardSer.pegarBoard();
    }

    @GetMapping("/{id}")
    public Board pegarBoardById(@PathVariable Long id){
        return boardSer.getBoardById(id);
    }

    @PutMapping("/{id}")
    public Board uptadeBoard(@PathVariable Long id, @RequestBody Board updatedBoard){
        return boardSer.updateBoard(id, updatedBoard);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id){
        boardSer.deleteBoard(id);
    }

}
