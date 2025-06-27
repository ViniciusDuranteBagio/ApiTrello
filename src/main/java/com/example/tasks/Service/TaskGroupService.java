package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class TaskGroupService {
    private final TaskGroupRepository repository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository repository, BoardRepository boardRepository) {
        this.repository = repository;
        this.boardRepository = boardRepository;
    }

    public List<TaskGroup> getAll() {
        return repository.findAll();
    }

    public TaskGroup getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskGroup não encontrado"));
    }

    public TaskGroup create(TaskGroup taskGroup, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Board inválido"));
        taskGroup.setBoard(board);
        return repository.save(taskGroup);
    }

    public TaskGroup update(Long id, TaskGroup update) {
        TaskGroup existing = getById(id);
        existing.setName(update.getName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskGroup não encontrado");
        }
        repository.deleteById(id);
    }
}