package com.ad.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.application.model.Story;
import com.ad.application.model.User;
import com.ad.application.service.UserServiceImpl;

@RestController
@RequestMapping(value="/api")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping(value="/user")
	public List<User> getIndex(){
		return userService.findAll();
	}
	
	@GetMapping(value="/user/{id}")
	public User getOne(@PathVariable Long id){
		return userService.findById(id);
	}
	
	@PostMapping(value="/user")
	public String getOne(User user){
		return userService.addUser(user);
	}
	
	@PutMapping(value="/user/{id}")
	public String update(User user, @PathVariable Long id){
		return userService.updateUser(user, id);
	}
	
	@DeleteMapping(value="/user/{id}")
	public String deleteUser(@PathVariable Long id){
		return userService.deleteById(id);
	}
	
	@PostMapping(value="/login")
	public List<Story> addUser(User user){
		return userService.authenticate(user.getEmail(), user.getPassword());
	}
	@PutMapping(value="/login")
	public List<Story> login(User user){
		return userService.authenticate(user.getEmail(), user.getPassword());
	}

	
}
