package com.example.tasks.service;

import com.example.tasks.dto.TaskGroupDTO;
import com.example.tasks.model.Board;
import com.example.tasks.model.TaskGroup;
import com.example.tasks.repository.BoardRepository;
import com.example.tasks.repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    public TaskGroupDTO createTaskGroup(TaskGroupDTO taskGroupDTO) {
        Board board = boardRepository.findById(taskGroupDTO.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board não encontrado com o id: " + taskGroupDTO.getBoardId()));

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(taskGroupDTO.getName());
        taskGroup.setBoard(board);

        TaskGroup savedTaskGroup = taskGroupRepository.save(taskGroup);

        return convertToDto(savedTaskGroup);
    }

    public List<TaskGroupDTO> getAllTaskGroups() {
        return taskGroupRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskGroupDTO getTaskGroupById(Long id) {
        TaskGroup taskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo de Tarefas não encontrado com o id: " + id));
        return convertToDto(taskGroup);
    }

    public TaskGroupDTO updateTaskGroup(Long id, TaskGroupDTO taskGroupDTO) {
        TaskGroup existingTaskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo de Tarefas não encontrado com o id: " + id));

        existingTaskGroup.setName(taskGroupDTO.getName());

        TaskGroup updatedTaskGroup = taskGroupRepository.save(existingTaskGroup);

        return convertToDto(updatedTaskGroup);
    }

    public void deleteTaskGroup(Long id) {
        if (!taskGroupRepository.existsById(id)) {
            throw new EntityNotFoundException("Grupo de Tarefas não encontrado com o id: " + id);
        }
        taskGroupRepository.deleteById(id);
    }

    // Este método auxiliar converte uma entidade TaskGroup em um TaskGroupDTO
    private TaskGroupDTO convertToDto(TaskGroup taskGroup) {
        TaskGroupDTO dto = new TaskGroupDTO();
        dto.setId(taskGroup.getId());
        dto.setName(taskGroup.getName());
        dto.setBoardId(taskGroup.getBoard().getId());
        return dto;
    }
}