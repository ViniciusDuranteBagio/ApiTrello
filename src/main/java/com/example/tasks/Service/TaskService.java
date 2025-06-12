package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        List<String> validStatuses = Arrays.asList("TODO", "IN_PROGRESS", "DONE");

        if (!validStatuses.contains(task.getStatus())) {
            throw new IllegalArgumentException("Status inválido! Os status permitidos são: TODO, IN_PROGRESS, DONE");
        }

        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada com o ID: " + id));
    }

    public Task update(Long id, Task taskDetails) {
        Task task = getById(id);

        List<String> validStatuses = Arrays.asList("TODO", "IN_PROGRESS", "DONE");
        if (!validStatuses.contains(taskDetails.getStatus())) {
            throw new IllegalArgumentException("Status inválido! Os status permitidos são: TODO, IN_PROGRESS, DONE");
        }

        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
