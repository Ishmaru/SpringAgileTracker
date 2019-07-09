package com.ad.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserControllerTest {
	
	@Autowired
	UserController userController;
	
	@Test
	public void whenRoutedToRoot_RecieveIndex() {
		String response = userController.getIndex();
		assertThat(response).isEqualTo("index");
	}


}
