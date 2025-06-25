package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskGroupRepository taskGroupRepository;


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        if (task.getTaskGroup() != null && task.getTaskGroup().getId() != null) {
            task.setTaskGroup(
                    taskGroupRepository.findById(task.getTaskGroup().getId())
                            .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"))
            );
        }
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());

            if (updatedTask.getTaskGroup() != null && updatedTask.getTaskGroup().getId() != null) {
                task.setTaskGroup(
                        taskGroupRepository.findById(updatedTask.getTaskGroup().getId())
                                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"))
                );
            }

            return taskRepository.save(task);
        });
    }

    // Deleta uma task
    public boolean deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
