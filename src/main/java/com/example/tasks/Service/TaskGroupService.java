package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepo;

    @Autowired
    private BoardRepository boardRepo;

    public TaskGroup create(Long boardId, TaskGroup taskGroup) {
        Board board = boardRepo.findById(boardId).orElseThrow(() -> new NoSuchElementException("Quadro não encontrado com o id: " + boardId));
        taskGroup.setBoard(board);
        return taskGroupRepo.save(taskGroup);
    }

    public List<TaskGroup> findAll() {
        return taskGroupRepo.findAll();
    }

    public TaskGroup findById(Long id) {
        return taskGroupRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Grupo de tarefas não encontrado com o id: " + id));
    }

    public TaskGroup update(Long id, TaskGroup group) {
        TaskGroup existing = findById(id);
        existing.setName(group.getName());
        return taskGroupRepo.save(existing);
    }

    public void delete(Long id) {
        taskGroupRepo.deleteById(id);
    }
}