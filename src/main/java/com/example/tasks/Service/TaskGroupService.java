package com.example.tasks.Service;

import com.example.tasks.Model.TaskGroup;
import com.example.tasks.Repository.BoardRepository;
import com.example.tasks.Repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;
    private final BoardRepository boardRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, BoardRepository boardRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.boardRepository = boardRepository;
    }
    // Create new Task Group
    public TaskGroup createTask(TaskGroup taskGroup){
        if (taskGroup.getTaskGroupName() == null || taskGroup.getTaskGroupName().length() < 3) {
            throw new IllegalArgumentException("Task group name must be at least 3 characters long");
        }
        if (taskGroup.getBoard() == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        Long boardId = taskGroup.getBoard().getBoardId();
        System.out.println("Board ID recebido: " + boardId); // log para debug
        if (boardId == null) {
            throw new IllegalArgumentException("Board ID cannot be null");
        }
        boolean exists = boardRepository.existsById(boardId);
        System.out.println("Board existe no repo? " + exists); // log para debug
        if (!exists) {
            throw new IllegalArgumentException("Board does not valid");
        }
        return taskGroupRepository.save(taskGroup);
    }

    // Get task group by id
    public TaskGroup getTaskServiceById(Long id) {
        return taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task group not found with id: " + id));
    }

    // Get all task groups frm the database
    public List<TaskGroup> getAllTaskGroups() {
        return taskGroupRepository.findAll();
    }

    // Update the task group
    public TaskGroup updateTaskGroup(Long id, TaskGroup updatedTaskGroup) {
        TaskGroup existingTaskGroup = taskGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task group not found with id: " + id));

        if (updatedTaskGroup.getTaskGroupName() != null && !updatedTaskGroup.getTaskGroupName().isEmpty()) {
            existingTaskGroup.setTaskGroupName(updatedTaskGroup.getTaskGroupName());
        }

        if (updatedTaskGroup.getBoard() != null) {
            boolean exists = boardRepository.existsById(updatedTaskGroup.getBoard().getBoardId());
            if (!exists) {
                throw new IllegalArgumentException("Board does not valid");
            }
            existingTaskGroup.setBoard(updatedTaskGroup.getBoard());
        }
        return taskGroupRepository.save(existingTaskGroup);
    }

    // Delete the task group and verify if it exists
    public void deleteTaskGroup(Long taskGroupId) {
        if (!taskGroupRepository.existsById(taskGroupId)) {
            throw new IllegalArgumentException("Task group not found with id: " + taskGroupId);
        }
        taskGroupRepository.deleteById(taskGroupId);
    }

}
