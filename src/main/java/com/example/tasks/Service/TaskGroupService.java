package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRep;
import com.example.tasks.Repository.TaskGroupRep;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskGroupSer {

    private final TaskGroupRep taskGroupRep;
    private final BoardRep boardRep;

    public TaskGroupSer(TaskGroupRep taskGroupRepository, BoardRep boardRep){
        this.taskGroupRep = taskGroupRepository;
        this.boardRep = boardRep;
    }

    public TaskGroup criarTask(TaskGroup taskGroup){
        if(taskGroup.getTaskGroupName() == null || taskGroup.getTaskGroupName().length() < 3){
            throw new IllegalArgumentException("O nome do task group não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if(taskGroup.getBoard() == null){
            throw new IllegalArgumentException("O task group não pode ter um board vazio.");
        }

        boolean exists = boardRep.existsById(taskGroup.getBoard().getBoardId());
        if(!exists){
            throw new IllegalArgumentException("O Board precisa ser válido.");
        }

        return taskGroupRep.save(taskGroup);
    }

    public List<TaskGroup> pegarTodasTasks(){return taskGroupRep.findAll();}

    public TaskGroup getTaskGroupById(Long id) {
        return taskGroupRep.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task não encontrada com id: " + id));
    }

    public TaskGroup updateTask(Long id, TaskGroup updatedTaskGroup){
        TaskGroup existingTaskGroup = getTaskGroupById(id);
        if(updatedTaskGroup.getTaskGroupName() == null || updatedTaskGroup.getTaskGroupName().length() < 3){
            throw new IllegalArgumentException("O nome não pode ser vazio ou ter menos que 3 caracteres.");
        }
        if (updatedTaskGroup.getBoard()!=existingTaskGroup.getBoard()){
            throw new IllegalArgumentException(" O board nao pode ser trocado. ");
        }
        existingTaskGroup.setTaskGroupName(updatedTaskGroup.getTaskGroupName());
        existingTaskGroup.setBoard(updatedTaskGroup.getBoard());
        return taskGroupRep.save(existingTaskGroup);
    }

    public void deleteTask(Long id){
        TaskGroup taskGroup = getTaskGroupById(id);
        taskGroupRep.delete(taskGroup);
    }
}
