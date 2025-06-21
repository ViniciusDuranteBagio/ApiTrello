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

    public List<TaskGroup> findAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> findGroupById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public TaskGroup updateTaskGroup(Long id, TaskGroup groupDetails) {
        TaskGroup group = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao encontrou id: " + id));

        group.setName(groupDetails.getName());

        return taskGroupRepository.save(group);
    }

    public void deleteTaskGroup(Long id) {
        TaskGroup group = taskGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao encontrou id: " + id));
        taskGroupRepository.delete(group);
    }
}