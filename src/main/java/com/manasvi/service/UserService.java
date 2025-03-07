package com.manasvi.service;

import java.util.List;

import com.manasvi.entity.User;

public interface UserService {

	User addUser(User user);
	
	User updateUser(User user);
	
	User getUserById(Integer id);
	
	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	void deleteUserById(Integer id);
	List<User> getUsersByRole(String role);
}
