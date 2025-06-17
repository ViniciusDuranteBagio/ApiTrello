package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskService taskService, TaskRepository taskRepository){this.taskRepository = taskRepository;}

    public Task crateTask(Task task){
        if(task.getTaskTitle().isEmpty() || task.getTaskTitle().length() < 3){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if(task.getTaskId() == null || task.getTaskId() < 0){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if(task.getTaskStatus() == null){
            throw new IllegalArgumentException("o estatus da task não pode ser nulo.");
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Task não encontrada com id : " + id));
    }

    public Task updateTask(Long id, Task uptadetTask){
        Task existingTask = getTaskById(id);

        if(uptadetTask.getTaskTitle() == null || uptadetTask.getTaskTitle().length() < 3){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }
        existingTask.setTaskTitle(uptadetTask.getTaskTitle());
        existingTask.setTaskDescription(uptadetTask.getTaskDescription());
        existingTask.setTaskStatus(uptadetTask.getTaskStatus());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id){
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
