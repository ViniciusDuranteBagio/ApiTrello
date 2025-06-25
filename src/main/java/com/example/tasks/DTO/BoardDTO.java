package com.example.tasks.DTO;

import com.example.tasks.Model.Board;

public class BoardDTO {
	private Long id;
	private String name;
	private String description;

	public BoardDTO() {
	}

	// Construtor para converter entidade em DTO
	public BoardDTO(Board board) {
		this.id = board.getId();
		this.name = board.getName();
		this.description = board.getDescription();
	}

	// Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}