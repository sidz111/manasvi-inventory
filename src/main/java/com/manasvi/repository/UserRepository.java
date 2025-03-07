package com.manasvi.repository;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
<<<<<<< HEAD
=======
	List<User> findByRole(String role);
>>>>>>> c3b96378c95209fc31272b058fba98816abf6ff5
}
