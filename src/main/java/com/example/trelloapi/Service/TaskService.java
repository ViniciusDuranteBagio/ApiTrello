package com.example.trelloapi.Service;

import com.example.trelloapi.Model.Task;
import com.example.trelloapi.Model.TaskGroup;
import com.example.trelloapi.Model.TaskStatus;
import com.example.trelloapi.Repository.TaskGroupRepository;
import com.example.trelloapi.Repository.TaskRepository;
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

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByTaskGroupId(Long taskGroupId) {
        return taskRepository.findByTaskGroupId(taskGroupId);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Título da Task é obrigatório.");
        }
        if (task.getStatus() == null) {
            throw new IllegalArgumentException("Status da Task é obrigatório.");
        }
        if (task.getTaskGroup() == null || task.getTaskGroup().getId() == null) {
            throw new IllegalArgumentException("Não é permitido criar Task sem TaskGroup.");
        }
        taskGroupRepository.findById(task.getTaskGroup().getId())
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup não encontrado com ID: " + task.getTaskGroup().getId()));

        return taskRepository.save(task);
    }

    public Task update(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task não encontrada com ID: " + id));

        if (taskDetails.getTitle() == null || taskDetails.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Título da Task é obrigatório.");
        }
        if (taskDetails.getStatus() == null) {
            throw new IllegalArgumentException("Status da Task é obrigatório.");
        }

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
