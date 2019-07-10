package com.ad.application.service;

import java.util.List;

import com.ad.application.model.Story;

public interface StoryServiceInt {
	List<Story> findAllById(Long userId);
	Story findById(Long id);
	String deleteById(Long id);
	String addStory(Story story, Long userId);
	String updateStory(Story story, Long id);
	String updateIteration(Story story, Long id);
}
