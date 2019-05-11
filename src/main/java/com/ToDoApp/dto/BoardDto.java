package com.ToDoApp.dto;

import java.util.List;


public class BoardDto {

	private Long id;
	private String name;
	private Long projectId;
	private Long userId;
	private List<Long> columnIds;

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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getColumnIds() {
		return columnIds;
	}

	public void setColumnIds(List<Long> columnIds) {
		this.columnIds = columnIds;
	}

	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", name=" + name + ", projectId=" + projectId + ", userId=" + userId
				+ ", columnIds=" + columnIds + "]";
	}
	
	
	
}
