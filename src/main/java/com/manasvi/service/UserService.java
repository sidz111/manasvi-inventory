package com.manasvi.service;

import java.util.List;

import com.manasvi.entity.User;

public interface UserService {

	User addUser(User user);
<<<<<<< HEAD
	
	User updateUser(User user);
	
	User getUserById(Integer id);
	
	User getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	void deleteUserById(Integer id);
=======
	User getUserByEmail(String email);
	User getUserById(Integer id);
	List<User> getAllUsers();
	List<User> getUsersByRole(String role);
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
}
