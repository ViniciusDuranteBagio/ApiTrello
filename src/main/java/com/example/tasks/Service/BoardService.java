package com.example.tasks.Service;

import com.example.tasks.Dtos.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board criarBoard(BoardDto boardDto) {
        Board board = new Board();

        board.setName(boardDto.getName());
        board.setDescription(boardDto.getDescription());

        return boardRepository.save(board);
    }
}
