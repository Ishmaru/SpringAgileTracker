package com.ad.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ad.application.model.Story;
@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {
	public Story findStoryById(Long id);
	public List<Story> findAllByUserId(Long userId);
	public Story findStoryByTitle(String title);
}
