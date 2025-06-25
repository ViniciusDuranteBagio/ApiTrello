package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupService {
    @Autowired
    private TaskGroupRepository taskGroupRepository;
    @Autowired
    private BoardRepository boardRepository;

    public TaskGroup createTaskGroup(Long BoardId,TaskGroup taskGroup) {
        if (taskGroup.getName() == null || taskGroup.getName().length() < 3) {
            throw new IllegalArgumentException("Nome do Task Group deve ter pelo menos 3 caracteres.");
        }
        Board board = boardRepository.findById(BoardId)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado com o ID: " + BoardId));
        taskGroup.setBoard(board);
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> getTaskGroupById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup taskGroupDetails) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task Group não encontrado com o ID: " + id));
        if (taskGroupDetails.getName() != null && taskGroupDetails.getName().length() >= 3) {
            taskGroup.setName(taskGroupDetails.getName());
        }
        return taskGroupRepository.save(taskGroup);
    }

    public void deleteTaskGroup(Long id) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task Group não encontrado com o ID: " + id));
        taskGroupRepository.delete(taskGroup);
    }

}
