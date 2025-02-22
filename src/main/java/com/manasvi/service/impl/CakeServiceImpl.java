package com.manasvi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manasvi.entity.Cake;
import com.manasvi.repository.CakeRepository;
import com.manasvi.service.CakeService;

@Service
public class CakeServiceImpl implements CakeService {
	
	@Autowired
	private CakeRepository cakeRepository;
	
	public CakeServiceImpl(CakeRepository cakeRepository) {
		super();
		this.cakeRepository = cakeRepository;
	}

	@Override
	public Cake addCake(Cake cake) {
		return this.cakeRepository.save(cake);
	}

	@Override
	public Cake updateCake(Cake cake) {
		Optional<Cake> c = cakeRepository.findById(cake.getId());
		if(c.isEmpty()) {
			return null;
		}
		else {
			cakeRepository.save(cake);
			return c.get();
		}
	}

	@Override
	public void deleteCakeById(Long id) {
		this.cakeRepository.deleteById(id);
	}

	@Override
	public List<Cake> getAllCakes() {
		return this.cakeRepository.findAll();
	}

	@Override
	public List<Cake> getCakesByDate(String date) {
		return this.cakeRepository.findByDate(date);
	}

	@Override
	public List<Cake> getCakesByforWho(String forWho) {
		return this.cakeRepository.findByForWho(forWho);
	}

	@Override
	public Optional<Cake> getCakeById(Long id) {
		Optional<Cake> c = cakeRepository.findById(id);
		if(c.isEmpty()) {
			return null;
		}
		else {
			return Optional.ofNullable(c.get());
		}
	}

}
