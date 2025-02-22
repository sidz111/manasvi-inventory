package com.manasvi.service;

import java.util.List;
import java.util.Optional;

import com.manasvi.entity.Cake;

public interface CakeService {
	
	Cake addCake(Cake cake);
	
	Cake updateCake(Cake cake);
	
	void deleteCakeById(Long id);
	
	List<Cake> getAllCakes();
	
	List<Cake> getCakesByDate(String date);
	
	List<Cake> getCakesByforWho(String forWho);
	
	Optional<Cake> getCakeById(Long id);
	
	

}
