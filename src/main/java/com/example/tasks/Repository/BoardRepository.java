package com.example.tasks.Repository;

import com.example.tasks.Model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Override
    Optional<Board> findById(Long id);
    List<Board>  findByNomeContainingIgnoreCase(String nome);
}
