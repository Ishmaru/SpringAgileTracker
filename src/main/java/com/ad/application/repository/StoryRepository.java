package com.ad.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ad.application.models.Story;
@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {
	public Story findStoryById(Long id);
}
