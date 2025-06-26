package com.example.tasks.service;

import com.example.tasks.dto.Dtos;
import com.example.tasks.model.Board;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardService boardService; // Usado para validar a existência do Board

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardService boardService) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardService = boardService;
    }

    @Transactional
    public Dtos.TaskGroupResponse createTaskGroup(Dtos.TaskGroupRequest request) {
        Board board = boardService.findBoardById(request.boardId()); // Valida se o board existe

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(request.name());
        taskGroup.setBoard(board);

        TaskGroup savedTaskGroup = taskGroupRepository.save(taskGroup);
        return toTaskGroupResponse(savedTaskGroup);
    }

    @Transactional(readOnly = true)
    public List<Dtos.TaskGroupResponse> getAllTaskGroups() {
        return taskGroupRepository.findAll().stream()
                .map(this::toTaskGroupResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Dtos.TaskGroupResponse getTaskGroupById(Long id) {
        TaskGroup taskGroup = findTaskGroupById(id);
        return toTaskGroupResponse(taskGroup);
    }

    @Transactional
    public Dtos.TaskGroupResponse updateTaskGroup(Long id, Dtos.TaskGroupRequest request) {
        TaskGroup taskGroup = findTaskGroupById(id);
        Board board = boardService.findBoardById(request.boardId()); // Valida o novo board se for o caso

        taskGroup.setName(request.name());
        taskGroup.setBoard(board);

        TaskGroup updatedTaskGroup = taskGroupRepository.save(taskGroup);
        return toTaskGroupResponse(updatedTaskGroup);
    }

    @Transactional
    public void deleteTaskGroup(Long id) {
        if (!taskGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Grupo de Tarefas não encontrado com o id: " + id);
        }
        taskGroupRepository.deleteById(id);
    }

    // Método auxiliar para encontrar TaskGroup ou lançar exceção
    public TaskGroup findTaskGroupById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo de Tarefas não encontrado com o id: " + id));
    }

    // Método auxiliar para conversão de Entidade para DTO
    private Dtos.TaskGroupResponse toTaskGroupResponse(TaskGroup taskGroup) {
        return new Dtos.TaskGroupResponse(taskGroup.getId(), taskGroup.getName(), taskGroup.getBoard().getId());
    }
}
