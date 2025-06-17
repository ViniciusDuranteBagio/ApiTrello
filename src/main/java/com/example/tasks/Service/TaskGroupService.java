package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository){
        this.taskGroupRepository = taskGroupRepository;
    }

    public TaskGroup createTaskGroup(TaskGroup taskGroup){
        if(taskGroup.getTaskGroupId() < 0){
            throw new IllegalArgumentException("O ID do task group não pode ser negativo");
        }
        if(taskGroup.getTaskGroupName().isEmpty() || taskGroup.getTaskGroupName().length() < 3){
            throw new IllegalArgumentException("O nome do task group não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if(taskGroup.getBoard() == null){
            throw new IllegalArgumentException("O task group não pode ter um board vazio.");
        }
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> getAllTaskGroups(){return taskGroupRepository.findAll();}

    public TaskGroup getTaskGroupById(Long id){
        return taskGroupRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Task não encontrada com id: " + id));
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup updatedTaskGroup){
        TaskGroup existingTaskGroup = getTaskGroupById(id);
        if(updatedTaskGroup.getTaskGroupName() == null || updatedTaskGroup.getTaskGroupName().length() < 3){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }
    }
}
