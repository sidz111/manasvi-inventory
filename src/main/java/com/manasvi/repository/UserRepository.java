package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    List<User> findByRole(String role);
    User findByEmail(String email);
}