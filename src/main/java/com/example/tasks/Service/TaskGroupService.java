package com.example.tasks.Service;

import com.example.tasks.Dtos.TaskGroupDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;


    public TaskGroup criarTaskGroup(Long boardId, TaskGroupDto taskGroupDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board com ID " + boardId + " não encontrado."));

        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(taskGroupDto.getName());
        taskGroup.setBoard(taskGroupDto.getBoard());
        taskGroup.setBoard(board);
        board.getListaDeTaskGroups().add(taskGroup);
        return taskGroupRepository.save(taskGroup);
    }

}
