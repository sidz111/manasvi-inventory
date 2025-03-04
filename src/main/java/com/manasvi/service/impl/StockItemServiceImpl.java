package com.manasvi.service.impl;

import com.manasvi.entity.StockHistory;
import com.manasvi.entity.StockItem;
import com.manasvi.repository.StockHistoryRepository;
import com.manasvi.repository.StockItemRepository;
import com.manasvi.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;
    
    @Autowired
    private StockHistoryRepository stockHistoryRepository;

    @Override
    public StockItem addStockItem(StockItem stockItem) {
        return this.stockItemRepository.save(stockItem);
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

    @Override
    public void recordStockHistory(Long stockItemId, String action, Integer quantity, Integer oldCount, Integer currentCount, Integer removedItem) {
        Optional<StockItem> stockItemOpt = stockItemRepository.findById(stockItemId);
        if (stockItemOpt.isPresent()) {
            StockItem stockItem = stockItemOpt.get();

            StockHistory stockHistory = new StockHistory();
            stockHistory.setStockItem(stockItem);
            stockHistory.setAction(action);
            stockHistory.setTimestamp(new Date().toString());
            stockHistory.setOldCount(oldCount);
            stockHistory.setCurrentCount(currentCount);
            stockHistory.setRemovedItem(removedItem);
            stockHistoryRepository.save(stockHistory);

            // Ensure history list is not null and add new record
            List<StockHistory> historyRecords = stockItem.getHistoryRecords();
            if (historyRecords == null) {
                historyRecords = new ArrayList<>();
            }
            historyRecords.add(stockHistory);
            stockItem.setHistoryRecords(historyRecords);
            
            stockItemRepository.save(stockItem);
        }
    }

}
