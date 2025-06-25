package com.example.tasks.DTO;

import com.example.tasks.Model.Task;

public class TaskDTO {
	private String name;
	private String description;
	private String status;
	private Long taskGroupId;

	public TaskDTO() {
	}

	public TaskDTO(Task task) {
		this.name = task.getName();
		this.description = task.getDescription();
		this.status = task.getStatus();
		this.taskGroupId = task.getTaskGroup() != null ? task.getTaskGroup().getId() : null;
	}

	// getters e setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTaskGroupId() {
		return taskGroupId;
	}

	public void setTaskGroupId(Long taskGroupId) {
		this.taskGroupId = taskGroupId;
	}
}
