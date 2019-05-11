package com.ToDoApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {

	@Id
    @GeneratedValue
    private Long id;
	
	private String title;
	
	private String description;
	
	private String priority;
	
	private String type;
	
	private Long listId;

	@ManyToOne
	private User owner;

	@ManyToOne
	private User asignee;
		
	public Task() {
		// TODO Auto-generated constructor stub
	}
	
	public Task(String title, String description, String priority, String type) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.type = type;
	}



	public Task(String title, String description, String priority, String type, Long listId,
			User owner, User asignee) {
		super();
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.type = type;
		this.listId = listId;
		this.owner = owner;
		this.asignee = asignee;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getAsignee() {
		return asignee;
	}

	public void setAsignee(User asignee) {
		this.asignee = asignee;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", type=" + type + ", listId=" + listId + ", owner=" + owner + ", asignee=" + asignee + "]";
	}

	public Task(Long id, String title, String description, String priority, String type, Long listId, User owner,
			User asignee) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.type = type;
		this.listId = listId;
		this.owner = owner;
		this.asignee = asignee;
	}
	
	

}
