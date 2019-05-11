package com.ToDoApp.dto;

import java.util.List;

public class ProjectDto {

    private Long id;
	private String name;
	private List<Long> boardIds;

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

	public List<Long> getBoardIds() {
		return boardIds;
	}

	public void setBoardIds(List<Long> boardIds) {
		this.boardIds = boardIds;
	}

	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", name=" + name + ", boardIds=" + boardIds + "]";
	}
	
	
	
}
