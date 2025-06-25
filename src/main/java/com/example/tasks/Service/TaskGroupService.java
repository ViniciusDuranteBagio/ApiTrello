package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupService {

    @Autowired
    private TaskGroupRepository taskGroupRepository;

    public TaskGroup createGroup(TaskGroup group) {
        return taskGroupRepository.save(group);
    }

    public List<TaskGroup> getAllGroups() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> getGroupById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public Optional<TaskGroup> updateGroup(Long id, TaskGroup updatedGroup) {
        return taskGroupRepository.findById(id).map(group -> {
            group.setName(updatedGroup.getName());
            group.setBoard(updatedGroup.getBoard());
            return taskGroupRepository.save(group);
        });
    }

    public boolean deleteGroup(Long id) {
        Optional<TaskGroup> group = taskGroupRepository.findById(id);
        if (group.isPresent()) {
            taskGroupRepository.deleteById(id);
            return true;
        }
        return false;
    }
}