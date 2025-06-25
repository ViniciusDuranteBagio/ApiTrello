package com.example.tasks.Repository;

import com.example.tasks.Model.Status;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskGroupId(Long taskGroupId);
    List<Task> findByStatus(Status status);



}
