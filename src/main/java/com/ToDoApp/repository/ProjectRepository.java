package com.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoApp.model.Project;

import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}
