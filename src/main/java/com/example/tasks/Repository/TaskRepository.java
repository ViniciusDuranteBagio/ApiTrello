package com.example.tasks.Repository;

import com.example.tasks.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.taskGroup.id = :taskGroupId")
    List<Task> findByTaskGroupId(@Param("taskGroupId") Long taskGroupId);
}