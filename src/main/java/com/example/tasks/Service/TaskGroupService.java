package com.example.tasks.Service;

import com.example.tasks.Dto.TaskGroupDto;
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

    public TaskGroup criarTaskGroup(TaskGroupDto dto) {
        if (dto.getName() == null || dto.getName().length() < 3) {
            throw new IllegalArgumentException("O nome do TaskGroup deve ter pelo menos 3 caracteres.");
        }
        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado"));
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(dto.getName());
        taskGroup.setBoard(board);
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> listarTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup buscarPorId(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TaskGroup não encontrado"));
    }

    public TaskGroup atualizarTaskGroup(Long id, TaskGroupDto dto) {
        TaskGroup taskGroup = buscarPorId(id);
        if (dto.getName() != null && dto.getName().length() >= 3) {
            taskGroup.setName(dto.getName());
        }
        return taskGroupRepository.save(taskGroup);
    }

    public void deletarTaskGroup(Long id) {
        TaskGroup taskGroup = buscarPorId(id);
        taskGroupRepository.delete(taskGroup);
    }
}