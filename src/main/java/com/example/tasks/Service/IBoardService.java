package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import java.util.List;

public interface IBoardService {
    Board createBoard(Board board);
    List<Board> getAllBoards();
    Board getBoardById(Long id);
    Board updateBoard(Long id, Board boardDetails);
    void deleteBoard(Long id);
}