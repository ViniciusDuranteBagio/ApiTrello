package com.example.tasks.Repository;

import com.example.tasks.Model.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardsRepository extends JpaRepository<Boards,Long> {
}
