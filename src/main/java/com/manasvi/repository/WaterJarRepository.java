package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.WaterJar;

@Repository
public interface WaterJarRepository extends JpaRepository<WaterJar, Long>{

	List<WaterJar> findByJarOwner(String jarOwner);
	List<WaterJar> findByDate(String date);
}
