package com.manasvi.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manasvi.entity.StockItem;
import com.manasvi.entity.Tea;
import com.manasvi.service.StockHistoryService;
import com.manasvi.service.StockItemService;
import com.manasvi.service.TeaService;
import com.manasvi.service.WaterJarService;

@Controller
@RequestMapping("/stock")
public class StockItemController {

	@Autowired
	private StockItemService stockItemService;

	@Autowired
	private TeaService teaService;

	@Autowired
	private WaterJarService waterJarService;

	@Autowired
	StockHistoryService stockHistoryService;

	@GetMapping("/list")
	public String listStockItems(Model model) {
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
		return "stock/list-stock";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("stockItem", new StockItem());
		model.addAttribute("totalStocks", stockItemService.getAllStockItems());
		model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
		model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());
		List<Tea> allTeas = teaService.getAllTeaRecords();

		int morningTeasTotal = allTeas.stream().filter(
				tea -> "Morning".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		int eveningTeasTotal = allTeas.stream().filter(
				tea -> "Evening".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		model.addAttribute("morningTeasTotal", morningTeasTotal);
		model.addAttribute("eveningTeasTotal", eveningTeasTotal);
		return "stock/add-stock";
	}

	@PostMapping("/save")
	public String saveStockItem(@ModelAttribute StockItem stockItem) {
		stockItemService.addStockItem(stockItem);
		return "redirect:/stock/add";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Optional<StockItem> stockItemOptional = stockItemService.getStockItemById(id);
		if (stockItemOptional.isPresent()) {
			model.addAttribute("stockItem", stockItemOptional.get()); // Pass the StockItem, not Optional
		} else {
			// Handle case where StockItem is not found
			return "redirect:/stock"; // Or show an error page
		}
		model.addAttribute("totalStocks", stockItemService.getAllStockItems());
		model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
		model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());

		List<Tea> allTeas = teaService.getAllTeaRecords();

		int morningTeasTotal = allTeas.stream().filter(
				tea -> "Morning".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		int eveningTeasTotal = allTeas.stream().filter(
				tea -> "Evening".equalsIgnoreCase(tea.getShip()) && extractDate(tea.getDate()).equals(LocalDate.now()))
				.mapToInt(Tea::getTotalTeaCups).sum();

		model.addAttribute("morningTeasTotal", morningTeasTotal);
		model.addAttribute("eveningTeasTotal", eveningTeasTotal);
		return "stock/edit-stock";
	}

	@PostMapping("/update/{id}")
	public String updateStockItem(@PathVariable Long id, @ModelAttribute StockItem stockItem) {
		stockItem.setId(id);
		stockItemService.updateStockItem(id, stockItem);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteStockItem(@PathVariable Long id) {
		stockItemService.deleteStockItem(id);
		return "redirect:/stock/add";
	}

	@GetMapping("/use-stock/{id}/{count}")
	public String useStocks(@PathVariable("id") Long id, @PathVariable("count") Integer no, Model model) {
	    Optional<StockItem> stockItem = stockItemService.getStockItemById(id);
	    
	    // Check if the stock item doesn't exist
	    if (stockItem.isEmpty()) {
	        model.addAttribute("error", "Class not found");
	        return "redirect:/stock/list";
	    }
	    
	    // Ensure there are enough stocks to use
	    else if (stockItem.get().getTotalStocks() < no || no <= 0) {
	        model.addAttribute("error", "Stock can't be used. Not enough stock or invalid amount.");
	        System.out.println("Stock can't be used. Not enough stock or invalid amount.");
	        return "stock/list-stock";
	    }

	    // If the stock exists and there are enough stocks, proceed with the update
	    else {
	        // Update the stock count after usage
	        int updatedTotalStock = stockItem.get().getTotalStocks() - no;
	        stockItem.get().setTotalStocks(updatedTotalStock);
	        
	        // Update the date and time
	        stockItem.get().setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        
	        // Record the stock usage in history
	        stockItemService.recordStockHistory(stockItem.get().getId(), "Used", no, 
	            updatedTotalStock + no, updatedTotalStock, no);
	        
	        // Save the updated stock item
	        stockItemService.addStockItem(stockItem.get());
	        
	        // Redirect to the stock list page
	        return "redirect:/stock/list";
	    }
	}


	private LocalDate extractDate(String dateTimeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
		return dateTime.toLocalDate();
	}
}
