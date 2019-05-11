package com.ToDoApp.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoApp.dto.BoardDto;
import com.ToDoApp.model.Board;
import com.ToDoApp.model.Project;
import com.ToDoApp.repository.BoardRepository;
import com.ToDoApp.repository.ColumnAggrRepository;
import com.ToDoApp.repository.ProjectRepository;
import com.ToDoApp.repository.UserRepository;
import com.ToDoApp.service.BoardService;

@CrossOrigin(origins = "*")
@RestController
public class BoardController {

	
	 private final Logger log = LoggerFactory.getLogger(BoardController.class);
	 
	 @Autowired
	 private BoardRepository boardRepository;
	 
	 @Autowired
	 private ProjectRepository projectRepository;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private ColumnAggrRepository columnAggrRepository;
	 
	 @Autowired
	 private BoardService boardService;


	 @GetMapping("/boards")
	 public List<Board> getBoards() {
		  log.info("Request to get all boards: {}");
		  return boardRepository.findAll();
	 }
	 
	 @GetMapping("/boards/{id}")
	 public Board getBoardById(@PathVariable Long id) {
		  log.info("Request to get board: {}", id);
			return boardRepository.findById(id).get();
	 }


	  @PostMapping("/boards")
	  public Board createBoard(@Valid @RequestBody BoardDto boardDto) {
		  log.info("Request to create/update board: {}", boardDto);
		    
		  //gather necessary data
		  Project project = null;
		  if(!StringUtils.isEmpty(boardDto.getProjectId())) {
			  project = projectRepository.findById(boardDto.getProjectId()).get();			  
		  }
		  
		  //create board
		  Board board = new Board(boardDto.getName());
		  
		  if(project != null) {
			  board.setProjectId(project.getId());			  
		  }
		  if(!StringUtils.isEmpty(boardDto.getUserId())) {
			  board.setUser(userRepository.findById(boardDto.getUserId()).get());			  
		  }
		  if(!StringUtils.isEmpty(boardDto.getColumnIds())) {
			  board.setColumns(columnAggrRepository.findAllById(boardDto.getColumnIds()));  
		  }
		  
		  board = boardRepository.save(board);
		  
		  //update project
		  if(project != null) {
			  project.addElementToBoards(board);
			  projectRepository.save(project);			  
		  }
		  
		  return board;
	  }
	  
	  @DeleteMapping("/boards/{id}")
 	  public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
	    log.info("Request to delete board: {}", id);
	    boardRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	  }
	  
	  
	  
	
}
