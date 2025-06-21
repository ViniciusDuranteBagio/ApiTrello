package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.DTO.TaskDTO;
import com.example.tasks.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskGroupRepository taskGroupRepository;

    public TaskService(TaskRepository taskRepository, TaskGroupRepository taskGroupRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        TaskGroup taskGroup = taskGroupRepository.findById(taskDTO.taskGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de tarefas não encontrado"));

        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());
        task.setTaskGroup(taskGroup);

        Task savedTask = taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByTaskGroup(Long taskGroupId) {
        return taskRepository.findByTaskGroupId(taskGroupId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());

        // Se mudar de grupo
        if (!task.getTaskGroup().getId().equals(taskDTO.taskGroupId())) {
            TaskGroup newTaskGroup = taskGroupRepository.findById(taskDTO.taskGroupId())
                    .orElseThrow(() -> new ResourceNotFoundException("Novo grupo de tarefas não encontrado"));
            task.setTaskGroup(newTaskGroup);
        }

        Task updatedTask = taskRepository.save(task);
        return convertToDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
        taskRepository.delete(task);
    }

    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getTaskGroup().getId()
        );
    }
}