package com.example.tasks.Service;

import com.example.tasks.Dto.Task.TaskCreateDto;
import com.example.tasks.Dto.Task.TaskDto;
import com.example.tasks.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITaskService {
    Task create(TaskCreateDto dto);
    Task update(Long id, TaskDto dto);
    Optional<Task> findById(Long id);
    Page<Task> findAll(Pageable pageable);
    void delete(Long id);
}
