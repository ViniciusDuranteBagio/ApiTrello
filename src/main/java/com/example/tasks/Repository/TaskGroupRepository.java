package com.example.tasks.Repository;

import com.example.tasks.Model.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroup,Long> {
}
