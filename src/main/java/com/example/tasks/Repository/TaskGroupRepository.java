package com.example.tasks.Repository;

import com.example.tasks.Model.TaskGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskGroup extends JpaRepository<TaskGroupModel, Long> {
}
