package com.ad.application;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.ad.application.models.User;
import com.ad.application.repository.UserRepository;
import com.ad.application.services.UserServiceImpl;
import com.ad.application.services.UserServiceInt;

@RunWith(SpringRunner.class)
public class UserServiceTest {

	@TestConfiguration
	static class SeviceConfiguration {
		@Bean
		public UserServiceInt userService() {
			return new UserServiceImpl();
		}
	}

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@MockBean
	UserRepository userRepository;
	
	User newUser;
	User repeatUser;
	@Before
	public void setUp() {
		newUser = new User("Bill", "bill@yahoo.com", "abc123");
		Mockito.when(userRepository.findUserByEmail(newUser.getEmail())).thenReturn(newUser);
	}
	@After
	public void tearDown() {
		userRepository.deleteAll();
	}
	@Test
	public void whenGivenEmail_thenReturnCorrectEmail() {
		String email = "bill@yahoo.com";
		User found = userServiceImpl.findByEmail("bill@yahoo.com");
		assertThat(email).isEqualTo(found.getEmail());
	}



}
