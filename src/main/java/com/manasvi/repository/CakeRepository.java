package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.Cake;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long>{

	List<Cake> findByForWho(String forWho);
	List<Cake> findByDate(String date);
}
