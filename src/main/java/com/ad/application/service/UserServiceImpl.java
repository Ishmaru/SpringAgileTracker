package com.ad.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.application.model.Story;
import com.ad.application.model.User;
import com.ad.application.repository.StoryRepository;
import com.ad.application.repository.UserRepository;



@Service
public class UserServiceImpl implements UserServiceInt{

	@Autowired
	UserRepository userRepository;

	@Autowired
	StoryRepository storyRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public String addUser(User user) {
		String returnString = "";
		try {
			userRepository.save(user);
			returnString = "User Added";
		}catch(Exception e) {
			returnString = "Failed to Post: \n" + e.getMessage();
		}
		return returnString;
	}

	@Override
	public String deleteById(Long id) {
		String returnString = "";
		try {
			userRepository.deleteById(id);
			returnString = "User Deleted";
		}catch(Exception e) {
			returnString = "Failed to Delete: \n" + e.getMessage();
		}
		return returnString;

	}

	@Override
	public User findById(Long id) {
		return userRepository.findUserById(id);
	}

	@Override
	public String updateUser(User user, Long id) {
		String returnString = "";
		
		try {
			User update = userRepository.findUserById(id);
			
			String tempEmail = validateUpdate(user.getEmail(), update.getEmail());
			String tempUsername = validateUpdate(user.getUsername(), update.getUsername());
			String tempPassword = validateUpdate(user.getPassword(), update.getPassword());
			
			update.setEmail(tempEmail);
			update.setUsername(tempUsername);
			update.setPassword(tempPassword);
			userRepository.save(update);
			
			returnString = "User Updated";
		}catch (Exception e) {
			returnString = "Cannot update: \n" + e.getMessage();
		}
		return returnString;
	}


	public static String validateUpdate (String incoming, String current) {
		return incoming != null ? incoming : current;
	}

	//Authentication
	public List<Story> authenticate(String email, String password) {
		User tempUser = userRepository.findUserByEmail(email);
		if(tempUser.getPassword().equals(password)) {
			return storyRepository.findAllByUserId(tempUser.getId());
		}
		return null;
	}
	
	public Boolean ifPermission(Long id, String password) {
		User tempUser = userRepository.findUserById(id);
		return tempUser.getPassword().equals(password);
	}
	

}
