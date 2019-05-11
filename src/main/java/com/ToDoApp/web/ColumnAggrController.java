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

import com.ToDoApp.dto.ColumnAggrDto;
import com.ToDoApp.model.Board;
import com.ToDoApp.model.ColumnAggr;
import com.ToDoApp.repository.BoardRepository;
import com.ToDoApp.repository.ColumnAggrRepository;
import com.ToDoApp.repository.TaskRepository;

@CrossOrigin(origins = "*")
@RestController
public class ColumnAggrController {

	
	 private final Logger log = LoggerFactory.getLogger(ColumnAggrController.class);
	 
	 @Autowired
	 private ColumnAggrRepository columnAggrRepository;
	 
	 @Autowired
	 private BoardRepository boardRepository;

	 @Autowired
	 private TaskRepository taskRepository;

	 @GetMapping("/columns")
	 public List<ColumnAggr> getColumns() {
		 log.info("Request to get all columns: {}");
		 return columnAggrRepository.findAll();
	 }
	 
	 @GetMapping("/columns/{id}")
	 public ColumnAggr getColumnById(@PathVariable Long id) {
		  log.info("Request to get column: {}", id);
			 return columnAggrRepository.findById(id).get();
	 }

	  @PostMapping("/columns")
	  public ColumnAggr createColumn(@Valid @RequestBody ColumnAggrDto columnAggrDto) {
		    log.info("Request to create/update column: {}", columnAggrDto);
			
		  //gather necessary data
		  Board board = null;
		  if(!StringUtils.isEmpty(columnAggrDto.getBoardId())) {
			  board = boardRepository.findById(columnAggrDto.getBoardId()).get();
		  }
		  
		  //create column
		  ColumnAggr column = null;
		  if(!StringUtils.isEmpty(columnAggrDto.getId())) {
			  column = columnAggrRepository.findById(columnAggrDto.getId()).get();			  
			  column.setName(columnAggrDto.getName());
		  } else {
			  column = new ColumnAggr(columnAggrDto.getName());
			    
		  }
		   
		  if(!StringUtils.isEmpty(columnAggrDto.getBoardId())) {
			  column.setBoardId(board.getId());
		  }
		  if(!StringUtils.isEmpty(columnAggrDto.getTaskIds())) {
			  column.setTasks(taskRepository.findAllById(columnAggrDto.getTaskIds()));			  
		  }
		  
		  column = columnAggrRepository.save(column);
		  
		  //update board
		  if(board != null) {
			  board.addElementToColumns(column);
			  boardRepository.save(board);
		  }
		  
		  return column;
	  }
	  
	  @DeleteMapping("/columns/{id}")
 	  public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
	    log.info("Request to delete column: {}", id);
	    columnAggrRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	  }
	  

	  
	  
	
}
