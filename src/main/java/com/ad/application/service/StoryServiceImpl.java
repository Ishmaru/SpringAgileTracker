package com.ad.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.application.model.Story;
import com.ad.application.model.Story.Iteration;
import com.ad.application.repository.StoryRepository;

@Service
public class StoryServiceImpl  implements StoryServiceInt{

	@Autowired
	StoryRepository storyRepository;
	
	
	@Override
	public List<Story> findAllStories() {
		System.out.println(storyRepository.findAll().get(0));
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

	private Iteration getIterationFromString(String iteration) {
		Iteration tempIteration = Iteration.BACKLOG;
		if(iteration.equals("sprint")) {
			tempIteration = Iteration.SPRINT;
		}else if(iteration.equals("testing")) {
			tempIteration = Iteration.TESTING;
		}else if(iteration.equals("complete")) {
			tempIteration = Iteration.COMPLETE;
		}
		return tempIteration;
	}
	
	@Override
	public String addStory(Story story, String iteration) {
		String returnString = "";
		if(storyRepository.findStoryByTitle(story.getTitle()) == null) {
			try {
				story.setSprintIteration(getIterationFromString(iteration));
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
		
		try {
			Story update = storyRepository.findStoryById(id);
			String tempTitle = validateUpdate(story.getTitle(), update.getTitle());
			String tempUserStory = validateUpdate(story.getUserStory(), update.getUserStory());
			String tempActionCriteria = validateUpdate(story.getActionCriteria(), update.getActionCriteria());
			Integer tempPoints = validateUpdate(story.getPoints(), update.getPoints());
//			Iteration tempIteration = validateUpdate(story.getSprintIteration(), update.getSprintIteration());

			update.setTitle(tempTitle);
			update.setUserStory(tempUserStory);
			update.setActionCriteria(tempActionCriteria);
			update.setPoints(tempPoints);
//			update.setSprintIteration(tempIteration);
			
			storyRepository.save(update);
			
			returnString = "Story Updated";
		}catch (Exception e) {
			returnString = "Cannot update: \n" + e.getMessage();
		}
		return returnString;
	}

	@Override
	public String updateIteration(Long id, String iteration) {
		String returnString = "";
		try {
			Story update = storyRepository.findStoryById(id);

			update.setSprintIteration(getIterationFromString(iteration));
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
	public static Integer validateUpdate (Integer incoming, Integer current) {
		return incoming != null ? incoming : current;
	}
	public static Iteration validateUpdate (Iteration incoming, Iteration current) {
		return incoming != null ? incoming : current;
	}
	

}
