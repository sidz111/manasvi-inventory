package com.manasvi.service;

import com.manasvi.entity.StockItem;
import java.util.List;
import java.util.Optional;

public interface StockItemService {

	StockItem addStockItem(StockItem stockItem);

	StockItem updateStockItem(Long id, StockItem stockItem);

	void deleteStockItem(Long id);

	Optional<StockItem> getStockItemById(Long id);

	List<StockItem> getAllStockItems();

	List<StockItem> findStockItemsByName(String name);

	List<StockItem> findStockItemsByAddedDate(String addedDate);

	List<StockItem> findStockItemsByUpdateDate(String updateDate);

	void recordStockHistory(Long stockItemId, String action, Integer quantity, Integer oldCount, Integer currentCount, Integer removedItem); // Store stock change history

}
