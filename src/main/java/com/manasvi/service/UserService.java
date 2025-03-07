package com.manasvi.service;

import java.util.List;

import com.manasvi.entity.User;

public interface UserService {

	User addUser(User user);
	User getUserByEmail(String email);
	User getUserById(Integer id);
	List<User> getAllUsers();
	List<User> getUsersByRole(String role);
}
