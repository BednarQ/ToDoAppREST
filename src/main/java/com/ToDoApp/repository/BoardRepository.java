package com.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ToDoApp.model.Board;
import com.ToDoApp.model.User;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
