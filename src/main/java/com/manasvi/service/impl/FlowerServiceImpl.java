package com.manasvi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.manasvi.entity.Flower;
import com.manasvi.repository.FlowerRepository;
import com.manasvi.service.FlowerService;

@Service
public class FlowerServiceImpl implements FlowerService {

	private FlowerRepository flowerRepository;

	public FlowerServiceImpl(FlowerRepository flowerRepository) {
		super();
		this.flowerRepository = flowerRepository;
	}

	@Override
	public Flower addFlower(Flower flower) {
		return this.flowerRepository.save(flower);
	}

	@Override
	public Flower UpdateFlower(Flower flower) {
		Optional<Flower> f = flowerRepository.findById(flower.getId());
		if (f.isEmpty()) {
			return null;
		} else {
			flowerRepository.save(f.get());
			return f.get();
		}
	}

	@Override
	public void deleteFlowerById(Long id) {
		this.flowerRepository.deleteById(id);
	}

	@Override
	public List<Flower> getAllFlowers() {
		return this.flowerRepository.findAll();
	}

	@Override
	public List<Flower> getAllFlowersByDate(String date) {
		List<Flower> fList = flowerRepository.findByDate(date);
		if (fList.size() < 1) {
			return null;
		} else {
			return fList;
		}
	}

	@Override
	public List<Flower> getAllFlowersBetweenDates(String startDate, String endDate) {
		List<Flower> fList = flowerRepository.findByDateBetween(startDate, endDate);
		if (fList.size() < 1) {
			return null;
		} else {
			return fList;
		}
	}
}
