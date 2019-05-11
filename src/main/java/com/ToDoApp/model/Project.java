package com.ToDoApp.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {

	@Id
    @GeneratedValue
    private Long id;
	
	private String name;
	
	@OneToMany(orphanRemoval = true, mappedBy = "projectId")
	private List<Board> boards;
	

	public Project() {
		super();
	}

	public Project(String name) {
		super();
		this.name = name;
	}

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

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}
	
	
	//add new element to boards list
	public void addElementToBoards(Board board) {
		if(this.boards != null) {
			if(!this.boards.contains(board)) {
				this.boards.add(board);
			}
		} else {
			this.boards = Arrays.asList(board);
		}
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", boards=" + boards + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boards == null) ? 0 : boards.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (boards == null) {
			if (other.boards != null)
				return false;
		} else if (!boards.equals(other.boards))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
