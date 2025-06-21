package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.DTO.TaskGroupDTO;
import com.example.tasks.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public TaskGroupDTO createTaskGroup(TaskGroupDTO taskGroupDTO) {
        Board board = boardRepository.findById(taskGroupDTO.boardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board não encontrado"));

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(taskGroupDTO.name());
        taskGroup.setBoard(board);

        TaskGroup savedTaskGroup = taskGroupRepository.save(taskGroup);
        return new TaskGroupDTO(savedTaskGroup.getId(), savedTaskGroup.getName(), board.getId());
    }

    @Transactional(readOnly = true)
    public List<TaskGroupDTO> getTaskGroupsByBoard(Long boardId) {
        return taskGroupRepository.findByBoardId(boardId).stream()
                .map(tg -> new TaskGroupDTO(tg.getId(), tg.getName(), tg.getBoard().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskGroupDTO updateTaskGroup(Long id, TaskGroupDTO taskGroupDTO) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de tarefas não encontrado"));

        taskGroup.setName(taskGroupDTO.name());
        TaskGroup updatedTaskGroup = taskGroupRepository.save(taskGroup);
        return new TaskGroupDTO(updatedTaskGroup.getId(), updatedTaskGroup.getName(), updatedTaskGroup.getBoard().getId());
    }

    @Transactional
    public void deleteTaskGroup(Long id) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo de tarefas não encontrado"));
        taskGroupRepository.delete(taskGroup);
    }
}