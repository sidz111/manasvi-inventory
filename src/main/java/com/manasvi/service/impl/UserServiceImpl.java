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
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
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
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public List<User> getUsersByRole(String role) {
		return this.userRepository.findByRole(role);
	}

}
