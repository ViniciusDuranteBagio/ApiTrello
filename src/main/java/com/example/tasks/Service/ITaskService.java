package com.example.tasks.Service;

import com.example.tasks.Model.Status;
import com.example.tasks.Model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> findAll();
    Task findById(Long id);
    Task save(Task task);
    void delete(Long id);
    List<Task> findByTaskGroupId(Long taskGroupId);
    List<Task> findByStatus(Status status);
}
