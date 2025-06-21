package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import com.example.tasks.dto.TaskGroupCreateDTO;
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

    public List<TaskGroup> findAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup createTaskGroup(TaskGroupCreateDTO dto) {
        Board board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board não encontrado com o id: " + dto.getBoardId()));

        TaskGroup newTaskGroup = new TaskGroup();
        newTaskGroup.setName(dto.getName());
        newTaskGroup.setBoard(board);

        return taskGroupRepository.save(newTaskGroup);
    }
    public Optional<TaskGroup> findGroupById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup groupDetails) {
        TaskGroup group = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado com o id: " + id));

        group.setName(groupDetails.getName());
        // Normalmente não se muda o board de um grupo, mas se fosse necessário, a lógica viria aqui.

        return taskGroupRepository.save(group);
    }

    public void deleteTaskGroup(Long id) {
        TaskGroup group = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado com o id: " + id));
        taskGroupRepository.delete(group);
    }
}