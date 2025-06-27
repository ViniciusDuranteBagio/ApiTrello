package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepo;

    public Board create(Board board) {
        return boardRepo.save(board);
    }

    public List<Board> findAll() {
        return boardRepo.findAll();
    }

    public Board findById(Long id) {
        return boardRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Quadro não encontrado com o id: " + id));
    }

    public Board update(Long id, Board boardDetails) {
        Board existingBoard = findById(id);
        existingBoard.setName(boardDetails.getName());
        existingBoard.setDescription(boardDetails.getDescription());
        return boardRepo.save(existingBoard);
    }

    public void delete(Long id) {
        Board boardToDelete = findById(id);
        boardRepo.delete(boardToDelete);
    }
}