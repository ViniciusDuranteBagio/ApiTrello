package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    //Constructor
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //Gets
    public Optional<Board> buscarPorId(Long id){
        return boardRepository.findById(id);
    }
    public List<Board>buscarPorNome(String nome){
        return boardRepository.findByNomeContainingIgnoreCase(nome);
    }

    //Post
    public Board salvar(Board board) {
        return boardRepository.save(board);
    }

    //Delete
    public void deletar(Long id) {
        boardRepository.deleteById(id);
    }
}
