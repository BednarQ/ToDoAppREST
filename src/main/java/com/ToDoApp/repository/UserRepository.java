package com.ToDoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoApp.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
	User findByEmail(String email);
}