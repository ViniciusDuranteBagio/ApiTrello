package com.example.tasks.Service;

import com.example.tasks.Dto.Task.TaskCreateDto;
import com.example.tasks.Dto.Task.TaskDto;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    @Override
    public Task create(TaskCreateDto dto) {
        TaskGroup group = taskGroupRepository.findById(dto.taskGroupId())
                .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));

        Task task = new Task();
        task.setName(dto.name());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setTaskGroup(group);

        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, TaskDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        task.setName(dto.name());
        task.setDescription(dto.description());
        task.setStatus(dto.status());

        if (!task.getTaskGroup().getId().equals(dto.taskGroupId())) {
            TaskGroup group = taskGroupRepository.findById(dto.taskGroupId())
                    .orElseThrow(() -> new RuntimeException("Grupo não encontrado"));
            task.setTaskGroup(group);
        }

        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
