package com.manasvi.service.impl;

import com.manasvi.entity.StockItem;
import com.manasvi.repository.StockItemRepository;
import com.manasvi.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;

    @Override
    public StockItem addStockItem(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    @Override
    public StockItem updateStockItem(Long id, StockItem updatedStockItem) {
        Optional<StockItem> existingStockItem = stockItemRepository.findById(id);
        if (existingStockItem.isPresent()) {
            StockItem stockItem = existingStockItem.get();
            stockItem.setName(updatedStockItem.getName());
            stockItem.setTotalStocks(updatedStockItem.getTotalStocks());
            stockItem.setAddedDate(updatedStockItem.getAddedDate());
            stockItem.setUpdateDate(updatedStockItem.getUpdateDate());
            return stockItemRepository.save(stockItem);
        } else {
            throw new RuntimeException("StockItem not found with ID: " + id);
        }
    }

    @Override
    public void deleteStockItem(Long id) {
        stockItemRepository.deleteById(id);
    }

    @Override
    public Optional<StockItem> getStockItemById(Long id) {
        return stockItemRepository.findById(id);
    }

    @Override
    public List<StockItem> getAllStockItems() {
        return stockItemRepository.findAll();
    }

    @Override
    public List<StockItem> findStockItemsByName(String name) {
        return stockItemRepository.findByName(name);
    }

    @Override
    public List<StockItem> findStockItemsByAddedDate(String addedDate) {
        return stockItemRepository.findByAddedDate(addedDate);
    }

    @Override
    public List<StockItem> findStockItemsByUpdateDate(String updateDate) {
        return stockItemRepository.findByUpdateDate(updateDate);
    }
}
