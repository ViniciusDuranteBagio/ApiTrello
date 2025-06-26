package com.example.tasks.Service;

import com.example.tasks.Dto.TaskGroup.TaskGroupCreateDto;
import com.example.tasks.Dto.TaskGroup.TaskGroupUpdateDto;
import com.example.tasks.Model.TaskGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITaskGroupService {
    TaskGroup create(TaskGroupCreateDto taskGroupDto);
    Page<TaskGroup> findAll(Pageable pageable);
    Optional<TaskGroup> findById(Long id);
    TaskGroup update(Long id, TaskGroupUpdateDto taskGroupUpdateDto);
    void delete(Long id);
}
