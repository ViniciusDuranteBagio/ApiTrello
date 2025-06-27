package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
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

    public TaskGroup saveTaskGroup(TaskGroup taskGroup) {
        Board board = boardRepository.findById(taskGroup.getBoard().getId())
                .orElseThrow(() -> new RuntimeException("Board not found"));

        TaskGroup newTaskGroup = new TaskGroup();
        newTaskGroup.setName(taskGroup.getName());
        newTaskGroup.setBoard(board);

        return taskGroupRepository.save(newTaskGroup);
    }

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> getTaskGroupById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup newData) {
        TaskGroup existing = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup not found"));
        existing.updateTaskGroup(newData);
        return taskGroupRepository.save(existing);
    }

    public void deleteTaskGroup(Long id) {
        taskGroupRepository.deleteById(id);
    }
}
