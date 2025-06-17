package com.example.tasks.Controller;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Service.TaskGroupService;
import com.example.tasks.Service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task_group")
public class TaskGroupController {

    private TaskGroupService taskGroupService;

    @GetMapping("/all")
    public List<TaskGroup> getAllTaskGroups(){
        List<TaskGroup> taskGroups = new ArrayList<>();
        taskGroups.add(new TaskGroup());
        return taskGroups;
    }

}
