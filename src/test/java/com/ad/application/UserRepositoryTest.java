package com.ad.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.application.models.User;
import com.ad.application.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Before
	public void setUp() {
		User testUser1 = new User("Ishmaru", "abc123", "ishmaru@gmail.com");
		userRepository.save(testUser1);
		User testUser2 = new User("Akari", "123abc", "akari@gmail.com");
		userRepository.save(testUser2);
	}
	
	@Test
	public void testGetUserNamebyId() {
		User result = userRepository.findById(1l).get();
		assertThat("Ishmaru").isEqualTo(result.getUsername());
	}
	
	@Test 
	public void testGetAllUsers() {
		List<User> results = userRepository.findAll();
		assertThat(2).isEqualTo(results.size());
	}

}
