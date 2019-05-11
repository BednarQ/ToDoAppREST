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

import com.ToDoApp.dto.ProjectDto;
import com.ToDoApp.model.ColumnAggr;
import com.ToDoApp.model.Project;
import com.ToDoApp.repository.BoardRepository;
import com.ToDoApp.repository.ProjectRepository;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {

	
	 private final Logger log = LoggerFactory.getLogger(ProjectController.class);
	 
	 @Autowired
	 private ProjectRepository projectRepository;
	 
	 @Autowired
	 private BoardRepository boardRepository;
	 
	 @GetMapping("/projects")
	 public List<Project> getProjects() {
		 log.info("Request to get all projects: {}");
		  return projectRepository.findAll();
	 }
	 
	 @GetMapping("/projects/{id}")
	 public Project getProjectById(@PathVariable Long id) {
		  log.info("Request to get project: {}", id);
		  return projectRepository.findById(id).get();
	 }

	  @PostMapping("/projects")
	  public Project createProject(@Valid @RequestBody ProjectDto projectDto) {
		  log.info("Request to create/update project: {}", projectDto);
		  
		  Project project = new Project(projectDto.getName());
		  
		  if(!StringUtils.isEmpty(projectDto.getBoardIds())) {
			  project.setBoards(boardRepository.findAllById(projectDto.getBoardIds()));			  
		  }
		  
		  return projectRepository.save(project);
	  }
	  
	  @DeleteMapping("/projects/{id}")
 	  public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
	    log.info("Request to delete project: {}", id);
	    projectRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	  }
	  
	
}
