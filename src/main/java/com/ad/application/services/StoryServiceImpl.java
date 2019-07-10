package com.ad.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.application.models.Story;
import com.ad.application.models.User;
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
		String returnString = "";
//		
//		try {
//			Story update = storyRepository.findStoryById(id);
//			
//			String tempIteration = validateUpdate(story.getEmail(), update.getEmail());
//
//			update.setEmail(tempEmail);
//
//			storyRepository.save(update);
//			
//			returnString = "Story Updated";
//		}catch (Exception e) {
//			returnString = "Cannot update: \n" + e.getMessage();
//		}
		return returnString;
	}

	@Override
	public String updateIteration(Story story, Long id) {
		String returnString = "";
		try {
			Story update = storyRepository.findStoryById(id);

			update.setSprintIteration(story.getSprintIteration());

			storyRepository.save(update);
			
			returnString = "Iteration Updated";
		}catch (Exception e) {
			returnString = "Cannot update: \n" + e.getMessage();
		}
		return returnString;
	}
	
	public static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}
	

}
