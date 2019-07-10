package com.ad.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.application.models.Story;
import com.ad.application.models.Story.Iteration;
import com.ad.application.repository.StoryRepository;
@RunWith(SpringRunner.class)
@DataJpaTest
public class StoryRepositoryTest {

	@Autowired
	StoryRepository storyRepository;
	
	Story testStory1;
	@Before
	public void setUp() {
		storyRepository.deleteAll();
		testStory1 = new Story("Add unit testing", "As a developer i would like to add a test for my StoryRepository", "Passing all tests", 32, Iteration.BACKLOG);
		storyRepository.save(testStory1);
	}
	
	@Test
	public void testGetFromDB() {
		Story testStory2 = storyRepository.findById(1L).get();
		assertThat(testStory2).isEqualToComparingFieldByField(testStory1);
	}

}
