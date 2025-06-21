// src/main/java/com/example/tasks/Service/TaskService.java
package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import com.example.tasks.dto.TaskCreateDTO;
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

    public Task saveTask(TaskCreateDTO dto) {
        // REGRA DE NEGÓCIO: Busca o TaskGroup para garantir que ele existe.
        TaskGroup taskGroup = taskGroupRepository.findById(dto.getTaskGroupId())
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado com o id: " + dto.getTaskGroupId()));

        // Cria a nova entidade Task
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setTaskGroup(taskGroup); // Faz a associação!

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada com o id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada com o id: " + id));
        taskRepository.delete(task);
    }
}