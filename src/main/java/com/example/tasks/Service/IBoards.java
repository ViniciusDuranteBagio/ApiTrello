package com.example.tasks.Service;

import com.example.tasks.Dto.Boards.BoardsDto;
import com.example.tasks.Model.Boards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBoards {
    Boards create(BoardsDto boardsDto);
    Page<Boards> findAll(Pageable pageable);
    Optional<Boards> findById(Long id);
    Boards update(Long id,BoardsDto boardsDto);

    void delete(Long id);
}
