package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private TaskGroupRepository taskGroupRepo;

    public Task create(Long taskGroupId, Task task) {
        TaskGroup group = taskGroupRepo.findById(taskGroupId).orElseThrow(() -> new NoSuchElementException("Grupo de tarefas não encontrado com o id: " + taskGroupId));
        task.setTaskGroup(group);
        return taskRepo.save(task);
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public Task findById(Long id) {
        return taskRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada com o id: " + id));
    }

    public Task update(Long id, Task task) {
        Task existing = findById(id);
        existing.setTitle(task.getTitle());
        existing.setDescription(task.getDescription());
        existing.setStatus(task.getStatus());
        return taskRepo.save(existing);
    }

    public void delete(Long id) {
        taskRepo.deleteById(id);
    }
}