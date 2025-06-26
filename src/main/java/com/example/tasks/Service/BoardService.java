package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class BoardSer {

    private final BoardRep boardRep;

    public BoardSer(BoardRep boardRep) {
        this.boardRep = boardRep;
    }

    public Board criarBoard(Board board){
        if(board.getBoardName() == null || board.getBoardName().length() < 3){
            throw new IllegalArgumentException("O nome nao pode ser vazio ou ter menos que 3 caracteres.");
        }
        return boardRep.save(board);
    }

    public List<Board> pegarBoard(){
        return boardRep.findAll();
    }

    public Board getBoardById(Long boardId){
        return boardRep.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board nao encontrado com id : " + boardId));
    }

    public Board updateBoard(Long boardId, Board updatedBoard){
        Board existingBoard = getBoardById(boardId);

        if(updatedBoard.getBoardName() == null || updatedBoard.getBoardName().length() < 3){
            throw new IllegalArgumentException("O nome nao pode ser vazio ou ter menos que 3 caracteres.");
        }

        existingBoard.setBoardName(updatedBoard.getBoardName());
        existingBoard.setBoardDescription(updatedBoard.getBoardDescription());
        return boardRep.save(existingBoard);
    }

    public void deleteBoard(Long boardId){
        Board board = getBoardById(boardId);
        boardRep.delete(board);
    }

}
