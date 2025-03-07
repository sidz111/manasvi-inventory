package com.manasvi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.manasvi.entity.User;
import com.manasvi.repository.UserRepository;

@SpringBootApplication
public class ManasviInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManasviInventoryApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("sssurwade2212@gmail.com")==null) {
                User admin = new User();
                admin.setEmail("sssurwade2212@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
                System.out.println("Admin user created successfully");
            }
        };
    }
}