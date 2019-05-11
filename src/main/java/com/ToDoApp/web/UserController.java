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

import com.ToDoApp.model.Task;
import com.ToDoApp.model.User;
import com.ToDoApp.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	
	 private final Logger log = LoggerFactory.getLogger(UserController.class);
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 

	 @GetMapping("/users")
	 public List<User> getUsers() {
		 log.info("Request to get all users: {}");
		 return userRepository.findAll();
	 }
	 
	 @GetMapping("/users/{id}")
	 public User getTaskById(@PathVariable Long id) {
		 log.info("Request to get user: {}", id);
		 return userRepository.findById(id).get();
	 }

	  @PostMapping("/users")
	  public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		  log.info("Request to create/update user: {}", user);
		  
		  User existingUser = null;
		  
		  if(!StringUtils.isEmpty(user.getEmail())) {
			  existingUser = userRepository.findByLogin(user.getLogin());
			  if(existingUser != null) {
				  return ResponseEntity.badRequest().body("User with that login already exists.");
			  }
		  }
		  
		  if(!StringUtils.isEmpty(user.getEmail())) {
			  existingUser = userRepository.findByEmail(user.getEmail());
			  if(existingUser != null) {
				  return ResponseEntity.badRequest().body("User with that email already exists.");
			  }
		  }
		  
		  User newUser = userRepository.save(user);
		  return ResponseEntity.ok().body(newUser);
		  
	  }
	  
	  @PutMapping("/users")
	    ResponseEntity<?> updateUser(@Valid @RequestBody User user) {
	        log.info("Request to update user: {}", user);
	        User result = userRepository.save(user);
	        return ResponseEntity.ok().body(result);
	    }
	  
	  @DeleteMapping("/users/{id}")
 	  public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
	    log.info("Request to delete user: {}", id);
	    
	    userRepository.deleteById(id);
	    return ResponseEntity.ok().build();
	  }
	  
	  
	
}
