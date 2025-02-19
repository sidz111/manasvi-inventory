package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {

	List<Flower> findByDate(String date);

	List<Flower> findByDateBetween(String startDate, String endDate);
}
