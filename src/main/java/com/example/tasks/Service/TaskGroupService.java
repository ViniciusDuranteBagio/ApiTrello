package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository){
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    public TaskGroup createTaskGroup(TaskGroup taskGroup){
        if(taskGroup.getTaskGroupName() == null || taskGroup.getTaskGroupName().length() < 3){
            throw new IllegalArgumentException("O nome do task group não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if(taskGroup.getBoard() == null){
            throw new IllegalArgumentException("O task group não pode ter um board vazio.");
        }
        boolean exists = boardRepository.existsById(taskGroup.getBoard().getBoardId());
        if(!exists){
            throw new IllegalArgumentException("O Board precisa ser válido.");
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
        if (!existingTaskGroup.getBoard().getBoardId().equals(updatedTaskGroup.getBoard().getBoardId())) {
            throw new IllegalArgumentException("O board não pode ser alterado.");
        }
        Board board = boardRepository.findById(updatedTaskGroup.getBoard().getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado"));

        existingTaskGroup.setTaskGroupName(updatedTaskGroup.getTaskGroupName());
        existingTaskGroup.setBoard(updatedTaskGroup.getBoard());
        return taskGroupRepository.save(existingTaskGroup);
    }

    public void deleteTaskGroup(Long id){
        TaskGroup taskGroup = getTaskGroupById(id);
        taskGroupRepository.delete(taskGroup);
    }
}
