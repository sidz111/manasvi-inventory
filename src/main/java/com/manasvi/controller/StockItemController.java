package com.manasvi.controller;

import com.manasvi.entity.StockItem;
import com.manasvi.service.StockItemService;
import com.manasvi.service.TeaService;
import com.manasvi.service.WaterJarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/stock")
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    @Autowired
    private TeaService teaService;

    @Autowired
    private WaterJarService waterJarService;

    @GetMapping
    public String listStockItems(Model model) {
        List<StockItem> stockItems = stockItemService.getAllStockItems();
        model.addAttribute("stockItems", stockItems);
        return "stock/list-stock";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("stockItem", new StockItem());
        model.addAttribute("totalStocks", stockItemService.getAllStockItems());
        model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
        model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());
        return "stock/add-stock";
    }

    @PostMapping("/save")
    public String saveStockItem(@ModelAttribute StockItem stockItem) {
        stockItemService.addStockItem(stockItem);
        return "redirect:/";
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
        return "stock/edit-stock";
    }

    @PostMapping("/update/{id}")
    public String updateStockItem(@PathVariable Long id, @ModelAttribute StockItem stockItem) {
        stockItem.setId(id); // Ensure the ID is set on the StockItem object
        stockItemService.updateStockItem(id, stockItem);
        return "redirect:/"; // Redirect to the home page or stock list after update
    }

    @GetMapping("/delete/{id}")
    public String deleteStockItem(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
        return "redirect:/"; // Redirect back to the home page after deletion
    }
}
