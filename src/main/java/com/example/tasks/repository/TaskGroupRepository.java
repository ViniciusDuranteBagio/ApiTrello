package com.example.tasks.repository;

import com.example.tasks.model.TaskGroup;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
}
