package com.example.tasks.Repository;

import com.example.tasks.Model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
    @Query("SELECT tg FROM TaskGroup tg WHERE tg.board.id = :boardId")
    List<TaskGroup> findByBoardId(@Param("boardId") Long boardId);
}