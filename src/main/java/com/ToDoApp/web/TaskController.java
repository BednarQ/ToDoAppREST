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

import com.ToDoApp.dto.TaskDto;
import com.ToDoApp.model.Board;
import com.ToDoApp.model.ColumnAggr;
import com.ToDoApp.model.Project;
import com.ToDoApp.model.Task;
import com.ToDoApp.repository.ColumnAggrRepository;
import com.ToDoApp.repository.TaskRepository;
import com.ToDoApp.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {

	
	 private final Logger log = LoggerFactory.getLogger(TaskController.class);
	 
	 @Autowired
	 private TaskRepository taskRepository;
	 
	 @Autowired
	 private ColumnAggrRepository columnAggrRepository;
	 
	 @Autowired
	 private UserRepository userRepository;


	 @GetMapping("/tasks")
	 public ResponseEntity<?> getTasks() {
		 log.info("Request to get all tasks");
		 return ResponseEntity.ok().body(taskRepository.findAll());
	 }
	 
	 @GetMapping("/tasks/{id}")
	 public ResponseEntity<?> getTaskById(@PathVariable Long id) {
		 log.info("Request to get task: {}", id);
		 return ResponseEntity.ok().body(taskRepository.findById(id).get());
	 }

	  @PostMapping("/tasks")
	  public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto taskDto) {
		  log.info("Request to create/update task: {}", taskDto);
		  
		  //gather necessary data
		  ColumnAggr columnAggr = null;
		  if(!StringUtils.isEmpty(taskDto.getListId())) {
			  columnAggr = columnAggrRepository.findById(taskDto.getListId()).get();
		  }
		  	  
		 //create task
		 Task task = null;
		 if(!StringUtils.isEmpty(taskDto.getId())) {
			 task = taskRepository.findById(taskDto.getId()).get();
			 task.setTitle(taskDto.getTitle());
			 task.setDescription(taskDto.getDescription());
			 task.setPriority(taskDto.getPriority());
			 task.setType(taskDto.getType());
		 } else {	 
			 task = TaskDto.map(taskDto);
		 }
		 
		 if(columnAggr != null) {
			 task.setListId(columnAggr.getId());			 
		 }
		 if(!StringUtils.isEmpty(taskDto.getOwnerId())) {
			 task.setOwner(userRepository.findById(taskDto.getOwnerId()).get());			 
		 }
		 if(!StringUtils.isEmpty(taskDto.getAsigneeId())) {
			 task.setAsignee(userRepository.findById(taskDto.getAsigneeId()).get());			 
		 }
		  
		 task = taskRepository.save(task); 
		 
		  //update column
		  if(columnAggr != null) {
			  columnAggr.addElementToTasks(task);
			  columnAggrRepository.save(columnAggr);
		  }
		  
		
		 return ResponseEntity.ok().body(task);
	  }
	  
	  @DeleteMapping("/tasks/{id}")
 	  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
	    log.info("Request to delete task: {}", id);
	    taskRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	  }
	  
	
}
