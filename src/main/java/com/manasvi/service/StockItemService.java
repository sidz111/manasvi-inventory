package com.manasvi.service;

import com.manasvi.entity.StockItem;
import java.util.List;
import java.util.Optional;

public interface StockItemService {
    
    // Add a new stock item
    StockItem addStockItem(StockItem stockItem);

    // Update an existing stock item
    StockItem updateStockItem(Long id, StockItem stockItem);

    // Delete a stock item by ID
    void deleteStockItem(Long id);

    // Get a stock item by ID
    Optional<StockItem> getStockItemById(Long id);

    // Get all stock items
    List<StockItem> getAllStockItems();

    // Find stock items by name
    List<StockItem> findStockItemsByName(String name);

    // Find stock items by added date
    List<StockItem> findStockItemsByAddedDate(String addedDate);

    // Find stock items by update date
    List<StockItem> findStockItemsByUpdateDate(String updateDate);
}
