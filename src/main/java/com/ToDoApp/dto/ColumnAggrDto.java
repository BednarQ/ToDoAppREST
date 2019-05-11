package com.ToDoApp.dto;

import java.util.List;

public class ColumnAggrDto {
	
	private Long id;
	private String name;
	private Long boardId;
	private List<Long> taskIds;

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

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public List<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<Long> taskIds) {
		this.taskIds = taskIds;
	}

	@Override
	public String toString() {
		return "ColumnAggrDto [id=" + id + ", name=" + name + ", boardId=" + boardId + ", taskIds=" + taskIds + "]";
	}

	
}
