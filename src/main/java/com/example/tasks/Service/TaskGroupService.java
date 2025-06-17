package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    public TaskGroup create(TaskGroup taskGroup) {
        Board board = boardRepository.findById(taskGroup.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado"));
        taskGroup.setBoard(board);
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> getAll() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup getById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));
    }

    public TaskGroup update(Long id, TaskGroup taskGroupAtualizado) {
        TaskGroup taskGroup = getById(id);
        taskGroup.setName(taskGroupAtualizado.getName());
        return taskGroupRepository.save(taskGroup);
    }

    public void delete(Long id) {
        TaskGroup taskGroup = getById(id);
        taskGroupRepository.delete(taskGroup);
    }
}
