package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TaskService implements ITaskService {

    private final TaskRepository repository;
    private final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository repository, TaskGroupRepository taskGroupRepository) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada com o ID: " + id));
    }

    @Transactional
    public Task save(Task task) {
        if (task.getTaskGroup() == null || task.getTaskGroup().getId() == null) {
            throw new ValidationException("TaskGroup não pode ser nulo");
        }

        TaskGroup taskGroup = taskGroupRepository.findById(task.getTaskGroup().getId())
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));

        task.setTaskGroup(taskGroup);

        return repository.save(task);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Task não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}

