package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {
   private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board){
       if(board.getBoardName() == null || board.getBoardName().length() < 3){
           throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
       }
       return boardRepository.save(board);
    }

    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    public Board getBoardById(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board não encontrado com id : " + boardId));
    }

    public Board updateBoard(Long boardId, Board updatedBoard){
        Board existingBoard = getBoardById(boardId);

        if(updatedBoard.getBoardName() == null || updatedBoard.getBoardName().length() < 3){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }

        existingBoard.setBoardName(updatedBoard.getBoardName());
        existingBoard.setBoardDescription(updatedBoard.getBoardDescription());
        return boardRepository.save(existingBoard);
    }

    public void deleteBoard(Long boardId){
        Board board = getBoardById(boardId);
        boardRepository.delete(board);
    }
}
