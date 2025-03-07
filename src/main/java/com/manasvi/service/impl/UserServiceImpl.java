package com.manasvi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manasvi.entity.User;
import com.manasvi.repository.UserRepository;
import com.manasvi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
<<<<<<< HEAD
	UserRepository userRepository;
=======
	private UserRepository userRepository;
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
<<<<<<< HEAD
	public User updateUser(User user) {
		Optional<User> u = Optional.ofNullable(userRepository.getById(user.getId()));
		if(u.isEmpty()) {
			return null;
		}
		else {
			return userRepository.save(u.get());	
		}
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> u = Optional.ofNullable(userRepository.getById(id));
		if(u.isEmpty()) {
			return null;
		}
		else {
			return u.get();
		}
	}

	@Override
=======
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
	public User getUserByEmail(String email) {
		Optional<User> u = Optional.ofNullable(userRepository.findByEmail(email));
		if(u.isEmpty()) {
			return null;
		}
		else {
			return u.get();
		}
	}

	@Override
<<<<<<< HEAD
=======
	public User getUserById(Integer id) {
		Optional<User> u = userRepository.findById(id);
		if(u.isEmpty()) {
			return null;
		}
		else {
			return u.get();
		}
	}

	@Override
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
<<<<<<< HEAD
	public void deleteUserById(Integer id) {
		this.userRepository.deleteById(id);
=======
	public List<User> getUsersByRole(String role) {
		return this.userRepository.findByRole(role);
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
	}

}
