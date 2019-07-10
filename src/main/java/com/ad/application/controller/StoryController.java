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
import com.ad.application.service.StoryServiceImpl;

@RestController
@RequestMapping(value="/api")
public class StoryController {

	@Autowired
	StoryServiceImpl storyService;
	
	@GetMapping(value="/user/{userId}/story")
	public List<Story> getIndex(@PathVariable Long userId) {
		return storyService.findAllById(userId);
	}
	
	@GetMapping(value="/user/{userId}/story/{id}")
	public Story getOne( @PathVariable Long userId, @PathVariable Long id){
		return storyService.findById(id);
	}
	
	@PostMapping(value="/user/{userId}/story")
	public String getOne(Story story, @PathVariable Long userId){
		return storyService.addStory(story, userId);
	}
	
	@PutMapping(value="user/{userId}/story/{id}")
	public String update(Story story, @PathVariable Long userId, @PathVariable Long id){
		return storyService.updateStory(story, id);
	}
	
	@DeleteMapping(value="/story/{id}")
	public String deleteStory(@PathVariable Long id){
		return storyService.deleteById(id);
	}
}
