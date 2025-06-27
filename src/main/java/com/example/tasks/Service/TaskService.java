package com.example.tasks.Service;

import com.example.tasks.Model.Task;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskGroupRepository taskGroupRepository = null;
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    // Create a new task
    public Task createTask(Task task) {
        if (task.getTaskTitle() == null || task.getTaskTitle().length() < 3) {
            throw new IllegalArgumentException("Task name must be at least 3 characters long");
        }

        if (task.getTaskStatus() == null) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        boolean exists = taskGroupRepository.existsById(task.getTaskGroup().getTaskGroupId());
        if (!exists) {
            throw new IllegalArgumentException("Task group does not valid");
        }

        return taskRepository.save(task);
    }

    // Retrieve a task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Taks not found with id: " + id));
    }

    // Get all tasks from the database
    public List<Task> getAllTasks() {
        return taskRepository.findAll(); // Get all tasks from the database
    }

    // Update the task
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));

        if (updatedTask.getTaskTitle() != null && !updatedTask.getTaskTitle().isEmpty()) {
            existingTask.setTaskTitle(updatedTask.getTaskTitle()); // Check if task title is not null or empty
        }

        if (updatedTask.getTaskStatus() != null) { // Check if task status is not null
            existingTask.setTaskStatus(updatedTask.getTaskStatus());
        }

        if (updatedTask.getTaskGroup() != null) {
            boolean exists = taskGroupRepository.existsById(updatedTask.getTaskGroup().getTaskGroupId());
            if (!exists) {
                throw new IllegalArgumentException("Task group does not valid");
            }
            existingTask.setTaskGroup(updatedTask.getTaskGroup());
        }
        return taskRepository.save(existingTask);
    }

    // Delete the task and verify if it exists
    public void deteteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("Task not found with id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

}
