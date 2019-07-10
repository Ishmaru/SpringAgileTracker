package com.ad.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.application.model.Story;
import com.ad.application.model.User;
import com.ad.application.model.Story.Iteration;
import com.ad.application.repository.StoryRepository;
import com.ad.application.service.StoryServiceImpl;
import com.ad.application.service.StoryServiceInt;

@RunWith(SpringRunner.class)
public class StoryServiceTest {

	@TestConfiguration
	static class SeviceConfiguration {
		@Bean
		public StoryServiceInt storyService() {
			return new StoryServiceImpl();
		}
	}
	
	@Autowired
	StoryServiceImpl storyServiceImpl;
	
	@MockBean
	StoryRepository storyRepository;
	
	Story newStory;
	@Before
	public void setUp() {
		newStory = new Story("Add unit testing", "As a developer i would like to add a test for my StoryRepository", "Passing all tests", 32, Iteration.BACKLOG, 1L);
		Mockito.when(storyRepository.findStoryByTitle("Add unit testing")).thenReturn(newStory);
		Mockito.when(storyRepository.save(newStory)).thenReturn(newStory);
	}
	@After
	public void tearDown() {
		storyRepository.deleteAll();
	}
	@Test
	public void whenGivenSimilar_thenRejectAddingToDB() {
		Story newStory2 = new Story("Add unit testing", "As a user i would like to see this similar message return a rejection message", "Passing test", 8, Iteration.SPRINT, 1L);
		String message = storyServiceImpl.addStory(newStory2, 1L);
		assertThat("Similar story already exits").isEqualTo(message);
	}
	@Test
	public void whenGivenUnique_thenAddToDB() {
		Story newStory2 = new Story("Quality Code", "As a user i want to make sure this app will not fail", "Passing test", 8, Iteration.TESTING, 1L);
		String message = storyServiceImpl.addStory(newStory2, 1L);
		assertThat("Story Added").isEqualTo(message);
	}
	@Test
	public void whenPutIteraton_Sucess() {
		Story newStory2 = new Story(null, "As a user i want to be able to switch Iteration", null, null, null, null);
		String message = storyServiceImpl.updateIteration(newStory2, newStory.getId());
		assertThat("Iteration Updated").isEqualTo(message);
	}

}
