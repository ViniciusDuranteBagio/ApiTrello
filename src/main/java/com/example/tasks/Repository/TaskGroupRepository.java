package com.example.tasks.Repository;

import com.example.tasks.Controller.TaskGroupController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroupController, Long> {
}