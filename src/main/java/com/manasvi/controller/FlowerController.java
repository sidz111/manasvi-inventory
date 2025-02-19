package com.manasvi.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manasvi.entity.Tea;
import com.manasvi.service.StockHistoryService;
import com.manasvi.service.StockItemService;
import com.manasvi.service.TeaService;
import com.manasvi.service.WaterJarService;

@Controller
@RequestMapping("/flower")
public class FlowerController {
	
	@Autowired
	StockItemService stockItemService;

	@Autowired
	TeaService teaService;

	@Autowired
	WaterJarService waterJarService;

	@Autowired
	StockHistoryService stockHistoryService;

	@GetMapping("/list")
	public String getFlowerPage(Model model) {
		model.addAttribute("totalStocks", stockItemService.getAllStockItems());
		model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
		model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());
		model.addAttribute("allStockHistories", stockHistoryService.getAllStockHistories());
		List<Tea> allTeas = teaService.getAllTeaRecords();

		int morningTeasTotal = allTeas.stream().filter(
				tea -> "Morning".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		int eveningTeasTotal = allTeas.stream().filter(
				tea -> "Evening".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		model.addAttribute("morningTeasTotal", morningTeasTotal);
		model.addAttribute("eveningTeasTotal", eveningTeasTotal);
		return "flower/flower-list";
	}
	
	private LocalDate extractDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime.toLocalDate();
    }
	
}
