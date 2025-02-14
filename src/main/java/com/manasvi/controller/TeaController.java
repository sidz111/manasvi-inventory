package com.manasvi.controller;

import com.manasvi.entity.Tea;
import com.manasvi.service.StockHistoryService;
import com.manasvi.service.StockItemService;
import com.manasvi.service.TeaService;
import com.manasvi.service.WaterJarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tea")
public class TeaController {

    @Autowired
    private TeaService teaService;
    
    @Autowired
    private StockItemService stockItemService;
    
    @Autowired
    private WaterJarService waterJarService;
    
    @Autowired
    private StockHistoryService stockHistoryService;

    @GetMapping
    public String listTea(Model model) {
        List<Tea> teas = teaService.getAllTeaRecords();
        model.addAttribute("teaList", teas);
        model.addAttribute("totalStocks", stockItemService.getAllStockItems());
        model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
        model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());
        model.addAttribute("allStockHistories", stockHistoryService.getAllStockHistories());
        Collections.reverse(teas);  // Reverse the list
        model.addAttribute("allTeas", teas);

        List<Tea> allTeas = teaService.getAllTeaRecords();

        int morningTeasTotal = allTeas.stream()
            .filter(tea -> "Morning".equalsIgnoreCase(tea.getShip()) && 
                    extractDate(tea.getDate()).equals(LocalDate.now()))
            .mapToInt(Tea::getTotalTeaCups)
            .sum();

        int eveningTeasTotal = allTeas.stream()
            .filter(tea -> "Evening".equalsIgnoreCase(tea.getShip()) && 
                    extractDate(tea.getDate()).equals(LocalDate.now()))
            .mapToInt(Tea::getTotalTeaCups)
            .sum();
        
        model.addAttribute("morningTeasTotal", morningTeasTotal);
        model.addAttribute("eveningTeasTotal", eveningTeasTotal);
        
        model.addAttribute("tea", new Tea());
        return "tea/list-tea";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "tea/add-tea";
    }

    @PostMapping("/save")
    public String saveTea(@ModelAttribute Tea tea) {
        tea.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        teaService.addTea(tea);
        return "redirect:/tea";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Tea> tea = teaService.getTeaById(id);
        model.addAttribute("tea", tea);
        return "tea/edit-tea";
    }

    @PostMapping("/update/{id}")
    public String updateTea(@PathVariable Long id, @ModelAttribute Tea tea) {
        teaService.updateTea(id, tea);
        return "redirect:/tea";
    }

    @GetMapping("/delete/{id}")
    public String deleteTea(@PathVariable Long id) {
        teaService.deleteTea(id);
        return "redirect:/tea";
    }

    private LocalDate extractDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime.toLocalDate();
    }
}
