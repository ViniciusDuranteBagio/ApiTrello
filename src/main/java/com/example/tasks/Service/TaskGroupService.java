package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    @Autowired
    private BoardRepository boardRepository;

    public TaskGroup createTaskGroup(TaskGroup taskGroup, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board não encontrado"));
        taskGroup.setBoard(board);
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public TaskGroup getTaskGroupById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"));
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup updatedGroup) {
        TaskGroup existing = getTaskGroupById(id);
        existing.setName(updatedGroup.getName());
        return taskGroupRepository.save(existing);
    }

    public void deleteTaskGroup(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
