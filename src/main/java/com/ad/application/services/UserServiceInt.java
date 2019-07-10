package com.ad.application.services;

import java.util.List;

import com.ad.application.models.User;

public interface UserServiceInt {
	User findByEmail(String email);
	List<User> findAll();
	User findById(Long id);
	String deleteById(Long id);
	String addUser(User user);
	String updateUser(User user, Long id);
}
