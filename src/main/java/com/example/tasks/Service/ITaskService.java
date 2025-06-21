package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import java.util.List;

public interface ITaskService {
    Task createTask(Task task, Long taskGroupId);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
}