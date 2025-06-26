package com.example.tasks.Repository;

import com.example.tasks.Model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRep extends JpaRepository<TaskGroup, Long> {
}
