package com.ad.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.application.models.Story;
import com.ad.application.repository.StoryRepository;

@Service
public class StoryServiceImpl  implements StoryServiceInt{

	@Autowired
	StoryRepository storyRepository;
	
	@Override
	public List<Story> findAll() {
		return storyRepository.findAll();
	}

	@Override
	public Story findById(Long id) {
		return storyRepository.findStoryById(id);
	}

	@Override
	public String deleteById(Long id) {
		String returnString = "";
		try {
			storyRepository.deleteById(id);
			returnString = "Story Deleted";
		}catch(Exception e) {
			returnString = "Failed to Delete: \n" + e.getMessage();
		}
		return returnString;
	}

	@Override
	public String addStory(Story story) {
		String returnString = "";
		if(storyRepository.findStoryByTitle(story.getTitle()) == null) {
			try {
				storyRepository.save(story);
				returnString = "Story Added";
			}catch(Exception e) {
				returnString = "Failed to Post: \n" + e.getMessage();
			}
		}else {
			returnString = "Similar story already exits";
		}
		return returnString;
	}
	
	@Override
	public String updateStory(Story story, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
