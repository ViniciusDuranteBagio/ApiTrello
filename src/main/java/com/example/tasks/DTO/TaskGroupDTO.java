package com.example.tasks.DTO;

import com.example.tasks.Model.TaskGroup;

public class TaskGroupDTO {
	private String name;
	private Long boardId;

	public TaskGroupDTO() {
	}

	public TaskGroupDTO(TaskGroup taskGroup) {
		this.name = taskGroup.getName();
		this.boardId = taskGroup.getBoard() != null ? taskGroup.getBoard().getId() : null;
	}

	// getters e setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
}
