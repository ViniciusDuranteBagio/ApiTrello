package com.example.tasks.Service;

import com.example.tasks.Dto.Boards.BoardsDto;
import com.example.tasks.Model.Boards;

import com.example.tasks.Repository.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class BoardsService implements IBoards {
    @Autowired
    private BoardsRepository boardsRepository;

    @Override
    public Boards create(BoardsDto boardsDto) {
        Boards board = new Boards();
        board.setName(boardsDto.name());
        board.setDescription(boardsDto.description());
        return boardsRepository.save(board);
    }

    @Override
    public Page<Boards> findAll(Pageable pageable) {
        return boardsRepository.findAll(pageable);
    }

    @Override
    public Optional<Boards> findById(Long id) {
        return boardsRepository.findById(id);
    }

    @Override
    public Boards update(Long id, BoardsDto boardsDto) {
        Boards boards = boardsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board não encontrado com id: " + id));

        if (boardsDto.name() != null && !boardsDto.name().isBlank()) {
            boards.setName(boardsDto.name());
        }

        if (boardsDto.description() != null && !boardsDto.description().isBlank()) {
            boards.setDescription(boardsDto.description());
        }

        return boardsRepository.save(boards);
    }

    @Override
    public void delete(Long id) {
        boardsRepository.deleteById(id);

    }
}
