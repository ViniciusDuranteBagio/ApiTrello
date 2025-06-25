package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task create(Task tarefa) {
        return taskRepository.save(tarefa);
    }
    public void deleteAll(){
        taskRepository.deleteAll();
    }
}