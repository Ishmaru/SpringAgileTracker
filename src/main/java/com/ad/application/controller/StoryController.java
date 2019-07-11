package com.ad.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping(value="/story")
	public List<Story> getIndex() {
		return storyService.findAllStories();
	}
	
	@GetMapping(value="/story/{id}")
	public Story getOne(@PathVariable Long id){
		return storyService.findById(id);
	}
	@CrossOrigin
	@PostMapping(value="/story/{iteration}")
	public String addStory(Story story, @PathVariable String iteration){
		return storyService.addStory(story, iteration);
	}
	
	@PutMapping(value="/story/{id}")
	public String update(Story story, @PathVariable Long id){
		return storyService.updateStory(story, id);
	}
	
	@PutMapping(value="/story/{id}/{iteration}")
	public String update(@PathVariable Long id, @PathVariable String iteration){
		return storyService.updateIteration(id, iteration);
	}
	
	@DeleteMapping(value="/story/{id}")
	public String deleteStory(@PathVariable Long id){
		return storyService.deleteById(id);
	}
}
