package com.example.tasks.Service;

import com.example.tasks.Dtos.BoardDto;
import com.example.tasks.Dtos.TaskGroupDto;
import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<TaskGroup> listarTodos(){
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> buscarPorId( Long id){
        return taskGroupRepository.findById(id);
    }

    public Optional<TaskGroup> atualizarTaskGroup(Long id, TaskGroupDto taskGroupDto){
        Optional<TaskGroup> taskGroupOptional = taskGroupRepository.findById(id);
        if (taskGroupOptional.isPresent()){
            TaskGroup taskGroup = taskGroupOptional.get();
            taskGroup.setName(taskGroupDto.getName());
            taskGroup.setBoard(taskGroupDto.getBoard());
            return  Optional.of(taskGroupRepository.save(taskGroup));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletarTaskGroup(Long id){
        Optional<TaskGroup> taskGroupOptional = taskGroupRepository.findById(id);
        if (taskGroupOptional.isPresent()) {
            taskGroupRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
