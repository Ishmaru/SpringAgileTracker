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

import com.ad.application.models.Story;
import com.ad.application.services.StoryServiceImpl;

@RestController
@RequestMapping(value="/api")
public class StoryController {

	@Autowired
	StoryServiceImpl storyService;
	
	@GetMapping(value="/story")
	public List<Story> getIndex() {
		return storyService.findAll();
	}
	
	@GetMapping(value="/story/{id}")
	public Story getOne(@PathVariable Long id){
		return storyService.findById(id);
	}
	
	@PostMapping(value="/story")
	public String getOne(Story story){
		String returnString = "";
		try {
			storyService.addStory(story);
			returnString = "Story Added";
		}catch(Exception e) {
			returnString = "Failed to Post: \n" + e.getMessage();
		}
		return returnString;
	}
	
	@PutMapping(value="/story/{id}")
	public String update(Story story, @PathVariable Long id){
		return storyService.updateStory(story, id);
	}
	
	@DeleteMapping(value="/story/{id}")
	public String deleteStory(@PathVariable Long id){
		return storyService.deleteById(id);
	}
}
