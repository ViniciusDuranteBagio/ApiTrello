package com.example.tasks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Task extends JpaRepository<com.example.tasks.Model.Task, Long> {
}
