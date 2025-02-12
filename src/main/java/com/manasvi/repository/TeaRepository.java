package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.Tea;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long>{

	List<Tea> findByDate(String date);
	List<Tea> findByType(String type);
}
