package com.example.tasks.service;

import com.example.tasks.dto.TaskGroupDto;
import com.example.tasks.model.Board;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.repository.BoardRepository;
import com.example.tasks.repository.TaskGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public Optional<TaskGroup> create(TaskGroupDto dto) {
        Optional<Board> boardOpt = boardRepository.findById(dto.getBoardId());
        if (boardOpt.isEmpty()) return Optional.empty();

        TaskGroup group = new TaskGroup();
        group.setName(dto.getName());
        group.setBoard(boardOpt.get());
        return Optional.of(taskGroupRepository.save(group));
    }

    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> findById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public Optional<TaskGroup> update(Long id, TaskGroupDto dto) {
        return taskGroupRepository.findById(id).flatMap(group -> {
            Optional<Board> boardOpt = boardRepository.findById(dto.getBoardId());
            if (boardOpt.isEmpty()) return Optional.empty();
            group.setName(dto.getName());
            group.setBoard(boardOpt.get());
            return Optional.of(taskGroupRepository.save(group));
        });
    }

    public boolean delete(Long id) {
        if (taskGroupRepository.existsById(id)) {
            taskGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
