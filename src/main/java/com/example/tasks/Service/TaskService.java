package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
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

    public Task createTask(Long taskGroupId, Task task) {
        if (task.getTitle() == null || task.getTitle().length() < 3) {
            throw new IllegalArgumentException("Titulo da Task é obrigatório e deve ter pelo menos 3 caracteres.");
        }
        if (task.getStatus() == null) {
            task.setStatus(Task.Status.TODO);
        }
        TaskGroup taskGroup = taskGroupRepository.findById(taskGroupId)
                .orElseThrow(() -> new IllegalArgumentException("Task Group não encontrado com o ID: " + taskGroupId));
        task.setTaskGroup(taskGroup);
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task não encontrada com o ID: " + id));
        if (taskDetails.getTitle() != null && taskDetails.getTitle().length() >= 3) {
            task.setTitle(taskDetails.getTitle());
        }
        if (taskDetails.getDescription() != null) {
            task.setDescription(taskDetails.getDescription());
        }
        if (taskDetails.getStatus() != null) {
            task.setStatus(taskDetails.getStatus());
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task não encontrada com o ID: " + id));
        taskRepository.delete(task);
    }
}
