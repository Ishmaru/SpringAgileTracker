package com.ad.application.service;

import java.util.List;

import com.ad.application.model.Story;

public interface StoryServiceInt {
	List<Story> findAllStories();
	Story findById(Long id);
	String deleteById(Long id);
	String addStory(Story story, String iteration);
	String updateStory(Story story, Long id);
	String updateIteration(Story story, Long id);
}
