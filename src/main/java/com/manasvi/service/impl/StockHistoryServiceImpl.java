package com.manasvi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manasvi.entity.StockHistory;
import com.manasvi.repository.StockHistoryRepository;
import com.manasvi.service.StockHistoryService;

@Service
public class StockHistoryServiceImpl implements StockHistoryService{
	
	@Autowired
	StockHistoryRepository stockHistoryRepository;

	@Override
	public List<StockHistory> getAllStockHistories() {
		return this.stockHistoryRepository.findAll();
	}

}
