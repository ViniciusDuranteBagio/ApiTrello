package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.Task;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    public TaskGroupService(BoardRepository boardRepository, TaskGroupRepository taskGroupRepository) {
        this.boardRepository = boardRepository;
        this.taskGroupRepository = taskGroupRepository;
    }

    public TaskGroup create(TaskGroup taskGroup) {
        Board board = boardRepository.findById(taskGroup.getBoard().getId())
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado"));
        taskGroup.setBoard(board);
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup findById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));
    }

    public TaskGroup update(Long id, TaskGroup updated) {
        TaskGroup existing = taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));
        existing.setName(updated.getName());
        return taskGroupRepository.save(existing);
    }

    public void delete(Long id) {
        taskGroupRepository.delete(findById(id));
    }
}
