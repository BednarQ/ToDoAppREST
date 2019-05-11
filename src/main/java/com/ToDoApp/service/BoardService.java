package com.ToDoApp.service;

import org.springframework.stereotype.Service;

import com.ToDoApp.model.Board;
import com.ToDoApp.model.Project;
import com.ToDoApp.repository.ProjectRepository;

@Service("boardService")
public class BoardService {

	public void addBoardToProjectData(Project project, Board board, ProjectRepository projectRepository) {
		//update project
		project.addElementToBoards(board);
	}
	
}
