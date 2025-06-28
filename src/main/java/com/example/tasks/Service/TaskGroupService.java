package com.example.tasks.Service;

import com.example.tasks.Model.Board;
import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.TaskGroupRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {
    @Autowired
    private TaskGroupRepository taskGroupRepo;

    public TaskGroup create(TaskGroup taskGroup) {
        return taskGroupRepo.save(taskGroup);
    }

    public List<TaskGroup> saveAll(List<TaskGroup> taskGroups) {
        return taskGroupRepo.saveAll(taskGroups);
    }

    public TaskGroup findById(Long id) {
        return taskGroupRepo.findById(id).orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"));
    }

    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepo.findAll(); // ✅ CERTO: busca do banco de dados
    }

    public List<TaskGroup> listAll() {
        return taskGroupRepo.findAll();
    }

    public TaskGroupService(TaskGroupRepository taskGroupRepo) {
        this.taskGroupRepo = taskGroupRepo;
    }

    public TaskGroup update(Long id, @Valid TaskGroup taskGroup) {
        TaskGroup existing = taskGroupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"));

        existing.setName(taskGroup.getName());
        existing.setBoard(taskGroup.getBoard());

        return taskGroupRepo.save(existing);
    }
    public void delete(Long id) {
        TaskGroup taskGroup = taskGroupRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TaskGroup não encontrado"));
        taskGroupRepo.delete(taskGroup);
    }



}
