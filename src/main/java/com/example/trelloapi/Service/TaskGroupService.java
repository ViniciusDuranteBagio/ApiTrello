package com.example.trelloapi.Service;

import com.example.trelloapi.Model.Board;
import com.example.trelloapi.Model.TaskGroup;
import com.example.trelloapi.Repository.BoardRepository;
import com.example.trelloapi.Repository.TaskGroupRepository;
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

    public List<TaskGroup> findAll() {
        return taskGroupRepository.findAll();
    }

    public List<TaskGroup> findByBoardId(Long boardId) {
        return taskGroupRepository.findByBoardId(boardId);
    }

    public Optional<TaskGroup> findById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public TaskGroup save(TaskGroup taskGroup) {
        if (taskGroup.getName() == null || taskGroup.getName().length() < 3) {
            throw new IllegalArgumentException("Nome do TaskGroup deve conter no mínimo 3 caracteres.");
        }
        if (taskGroup.getBoard() == null || taskGroup.getBoard().getId() == null) {
            throw new IllegalArgumentException("Não é permitido criar TaskGroup sem Board.");
        }
        boardRepository.findById(taskGroup.getBoard().getId())
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado com ID: " + taskGroup.getBoard().getId()));

        return taskGroupRepository.save(taskGroup);
    }

    public TaskGroup update(Long id, TaskGroup taskGroupDetails) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TaskGroup não encontrado com ID: " + id));

        if (taskGroupDetails.getName() == null || taskGroupDetails.getName().length() < 3) {
            throw new IllegalArgumentException("Nome do TaskGroup deve conter no mínimo 3 caracteres.");
        }

        taskGroup.setName(taskGroupDetails.getName());
        return taskGroupRepository.save(taskGroup);
    }

    public void deleteById(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
