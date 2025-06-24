package com.example.trelloapi.Repository;

import com.example.trelloapi.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskGroupId(Long taskGroupId);
}
