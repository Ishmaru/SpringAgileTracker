package com.ad.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ad.application.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findAll();
	public User findUserByEmail(String email);
	public User findUserById(Long id);

}
