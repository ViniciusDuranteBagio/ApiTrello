package com.example.tasks.Service;

import com.example.tasks.Dtos.BoardDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public List<Board> listarTodos(){
        return boardRepository.findAll();
    }

    public Optional<Board> buscarPorId(Long id){
        return  boardRepository.findById(id);
    }

    public Optional<Board> atualizarBoard(Long id, BoardDto boardDto){
        Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setName(boardDto.getName());
            board.setDescription(boardDto.getDescription());
            return Optional.of(boardRepository.save(board));
        } else {
            return Optional.empty();
        }
    }



}
