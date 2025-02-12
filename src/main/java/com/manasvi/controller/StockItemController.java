package com.manasvi.controller;

import com.manasvi.entity.StockItem;
import com.manasvi.service.StockItemService;
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

    @GetMapping
    public String listStockItems(Model model) {
        List<StockItem> stockItems = stockItemService.getAllStockItems();
        model.addAttribute("stockItems", stockItems);
        return "stock/list-stock";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("stockItem", new StockItem());
        return "stock/add-stock";
    }

    @PostMapping("/save")
    public String saveStockItem(@ModelAttribute StockItem stockItem) {
        stockItemService.addStockItem(stockItem);
        return "redirect:/stock";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<StockItem> stockItem = stockItemService.getStockItemById(id);
        model.addAttribute("stockItem", stockItem);
        return "stock/edit-stock";
    }

    @PostMapping("/update/{id}")
    public String updateStockItem(@PathVariable Long id, @ModelAttribute StockItem stockItem) {
        stockItemService.updateStockItem(id, stockItem);
        return "redirect:/stock";
    }

    @GetMapping("/delete/{id}")
    public String deleteStockItem(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
        return "redirect:/stock";
    }
}
