package com.example.tasks.Service;

import com.example.tasks.Dto.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Board> listarBoards() {
        return boardRepository.findAll();
    }

    public Board buscarPorId(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    public Board atualizarBoard(Long id, BoardDto boardDto) {
        Board board = buscarPorId(id);
        board.setName(boardDto.getName());
        board.setDescription(boardDto.getDescription());
        return boardRepository.save(board);
    }

    public void deletarBoard(Long id) {
        boardRepository.deleteById(id);
    }
}