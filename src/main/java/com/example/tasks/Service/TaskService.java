package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository tkRepository) {
        this.taskRepository = tkRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Task update(Long id, Task updtTask) {
        Task tg = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado"));

        tg.setName(updtTask.getName());

        return taskRepository.save(tg);
    }
}
