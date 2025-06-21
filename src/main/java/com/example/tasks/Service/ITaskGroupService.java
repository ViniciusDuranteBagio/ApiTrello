package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;

public interface ITaskGroupService {
    TaskGroup createTaskGroup(TaskGroup taskGroup, Long boardId);
    void deleteTaskGroup(Long id);
}