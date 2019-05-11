package com.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoApp.model.Task;


import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
