package com.ad.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad.application.models.User;
import com.ad.application.repository.UserRepository;



@Service
public class UserServiceImpl implements UserServiceInt{

  @Autowired
  UserRepository userRepository;

  @Override
  public User findByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public void addUser(User user) {
	  
    userRepository.save(user);
  }
  
  @Override
  public void deleteAll() {
    userRepository.deleteAll();
  }
  
  @Override
  public User findById(Long id) {
    return userRepository.findById(id).get();
  }
  
}
