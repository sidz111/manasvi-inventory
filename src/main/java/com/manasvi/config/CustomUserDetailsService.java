package com.manasvi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.manasvi.entity.User;
import com.manasvi.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = userRepository.findByEmail(email);
		if(u==null) {
			throw new UsernameNotFoundException("User Not found");
		}
		else {
			return new CustomUser(u);
		}
	}

}
