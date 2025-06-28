package com.example.tasks.service;

import com.example.tasks.dto.TaskDto;
import com.example.tasks.model.Task;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.repository.TaskGroupRepository;
import com.example.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;

    public Optional<Task> create(TaskDto dto) {
        Optional<TaskGroup> groupOpt = taskGroupRepository.findById(dto.getTaskGroupId());
        if (groupOpt.isEmpty()) return Optional.empty();

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setTaskGroup(groupOpt.get());

        return Optional.of(taskRepository.save(task));
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> update(Long id, TaskDto dto) {
        return taskRepository.findById(id).flatMap(task -> {
            Optional<TaskGroup> groupOpt = taskGroupRepository.findById(dto.getTaskGroupId());
            if (groupOpt.isEmpty()) return Optional.empty();

            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setTaskGroup(groupOpt.get());

            return Optional.of(taskRepository.save(task));
        });
    }

    public boolean delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
