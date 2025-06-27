package com.example.tasks.Service;

import com.example.tasks.Dto.TaskDto;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public TaskGroupRepository taskGroupRepository;

    public Task criarTask(TaskDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }
        TaskGroup taskGroup = taskGroupRepository.findById(dto.getTaskGroupId())
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        try {
            task.setStatus(Task.Status.valueOf(dto.getStatus()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Status inválido.");
        }
        task.setTaskGroup(taskGroup);
        return taskRepository.save(task);
    }

    public List<Task> listarTasks() {
        return taskRepository.findAll();
    }

    public Task buscarPorId(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task não encontrada"));
    }

    public Task atualizarTask(Long id, TaskDto dto) {
        Task task = buscarPorId(id);
        if (dto.getTitle() != null && !dto.getTitle().isBlank()) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            try {
                task.setStatus(Task.Status.valueOf(dto.getStatus()));
            } catch (Exception e) {
                throw new IllegalArgumentException("Status inválido.");
            }
        }
        return taskRepository.save(task);
    }

    public void deletarTask(Long id) {
        Task task = buscarPorId(id);
        taskRepository.delete(task);
    }
}