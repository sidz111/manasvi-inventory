package com.manasvi.service;

import java.util.List;

import com.manasvi.entity.Flower;

public interface FlowerService {

	Flower addFlower(Flower flower);
	
	Flower UpdateFlower(Flower flower);
	
	void deleteFlowerById(Long id);
	
	List<Flower> getAllFlowers();
	
	List<Flower> getAllFlowersByDate(String date);
	
	List<Flower> getAllFlowersBetweenDates(String startDate, String endDate);
}
