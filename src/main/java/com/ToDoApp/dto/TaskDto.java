package com.ToDoApp.dto;

import com.ToDoApp.model.Task;

public class TaskDto {

    private Long id;
	
	private String title;
	
	private String description;
	
	private String priority;
	
	private String type;
	
	private Long listId; //column

	private Long ownerId;

	private Long asigneeId;
	
	
	public static Task map(TaskDto taskDto) {
		return new Task(
				 taskDto.getTitle(),
				 taskDto.getDescription(),
				 taskDto.getPriority(),
				 taskDto.getType()
		);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getAsigneeId() {
		return asigneeId;
	}

	public void setAsigneeId(Long asigneeId) {
		this.asigneeId = asigneeId;
	}

	@Override
	public String toString() {
		return "TaskDto [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", type=" + type + ", listId=" + listId + ", ownerId=" + ownerId + ", asigneeId=" + asigneeId + "]";
	}
	
}
