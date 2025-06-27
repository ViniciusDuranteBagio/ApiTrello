package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    public TaskGroup create(TaskGroup taskGroup) {
        return taskGroupRepository.save(taskGroup);
    }

    public List<TaskGroup> getAll() {
        return taskGroupRepository.findAll();
    }

    public Optional<TaskGroup> getById(Long id) {
        return taskGroupRepository.findById(id);
    }

    public void delete(Long id) {
        taskGroupRepository.deleteById(id);
    }

    public TaskGroup update(Long id, TaskGroup updtTaskGroup) {
        TaskGroup tg = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board não encontrado"));

        tg.setName(updtTaskGroup.getName());

        return taskGroupRepository.save(tg);
    }

}
