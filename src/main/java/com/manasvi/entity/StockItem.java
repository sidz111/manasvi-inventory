package com.manasvi.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class StockItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer totalStocks;

	private String addedDate;
	private String updateDate;

	@OneToMany(mappedBy = "stockItem", cascade = CascadeType.ALL)
	private List<StockHistory> historyRecords;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalStocks() {
		return totalStocks;
	}

	public void setTotalStocks(Integer totalStocks) {
		this.totalStocks = totalStocks;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public List<StockHistory> getHistoryRecords() {
		return historyRecords;
	}

	public void setHistoryRecords(List<StockHistory> historyRecords) {
		this.historyRecords = historyRecords;
	}
}
