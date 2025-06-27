package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository repository, TaskGroupRepository taskGroupRepository) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada"));
    }

    public Task create(Task task, Long taskGroupId) {
        TaskGroup tg = taskGroupRepository.findById(taskGroupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "TaskGroup inválido"));
        task.setTaskGroup(tg);
        return repository.save(task);
    }

    public Task update(Long id, Task update) {
        Task existing = getById(id);
        existing.setTitle(update.getTitle());
        existing.setDescription(update.getDescription());
        existing.setStatus(update.getStatus());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada");
        }
        repository.deleteById(id);
    }
}