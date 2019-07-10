package com.ad.application.services;

import java.util.List;

import com.ad.application.models.Story;

public interface StoryServiceInt {
	List<Story> findAll();
	Story findById(Long id);
	String deleteById(Long id);
	String addStory(Story story);
	String updateStory(Story story, Long id);
}
