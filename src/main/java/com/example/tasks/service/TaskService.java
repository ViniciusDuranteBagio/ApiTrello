package com.example.tasks.service;

import com.example.tasks.dto.Dtos;
import com.example.tasks.model.Task;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskGroupService taskGroupService; // Usado para validar a existência do TaskGroup

    public TaskService(TaskRepository taskRepository, TaskGroupService taskGroupService) {
        this.taskRepository = taskRepository;
        this.taskGroupService = taskGroupService;
    }

    @Transactional
    public Dtos.TaskResponse createTask(Dtos.TaskRequest request) {
        TaskGroup taskGroup = taskGroupService.findTaskGroupById(request.taskGroupId()); // Valida se o grupo existe

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setTaskGroup(taskGroup);

        Task savedTask = taskRepository.save(task);
        return toTaskResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public List<Dtos.TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Dtos.TaskResponse getTaskById(Long id) {
        Task task = findTaskById(id);
        return toTaskResponse(task);
    }

    @Transactional
    public Dtos.TaskResponse updateTask(Long id, Dtos.TaskRequest request) {
        Task task = findTaskById(id);
        TaskGroup taskGroup = taskGroupService.findTaskGroupById(request.taskGroupId()); // Valida o novo grupo

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setTaskGroup(taskGroup);

        Task updatedTask = taskRepository.save(task);
        return toTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada com o id: " + id);
        }
        taskRepository.deleteById(id);
    }

    // Método auxiliar para encontrar Task ou lançar exceção
    private Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com o id: " + id));
    }

    // Método auxiliar para conversão de Entidade para DTO
    private Dtos.TaskResponse toTaskResponse(Task task) {
        return new Dtos.TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getTaskGroup().getId());
    }
}
