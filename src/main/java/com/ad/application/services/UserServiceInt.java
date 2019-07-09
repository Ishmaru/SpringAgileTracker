package com.ad.application.services;

import java.util.List;

import com.ad.application.models.User;

public interface UserServiceInt {

	User findByEmail(String email);

	List<User> findAll();

	User findById(Long id);

	void deleteAll();

	void addUser(User user);

}
