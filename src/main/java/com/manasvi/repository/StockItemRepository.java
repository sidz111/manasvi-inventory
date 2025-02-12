package com.manasvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manasvi.entity.StockItem;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    List<StockItem> findByName(String name);

    List<StockItem> findByAddedDate(String addedDate);

    List<StockItem> findByUpdateDate(String updateDate);
}
