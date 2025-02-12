package com.manasvi.controller;

import com.manasvi.entity.Tea;
import com.manasvi.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tea")
public class TeaController {

    @Autowired
    private TeaService teaService;

    @GetMapping
    public String listTea(Model model) {
        List<Tea> teas = teaService.getAllTeaRecords();
        model.addAttribute("teaList", teas);
        return "tea/list-tea";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tea", new Tea());
        return "tea/add-tea";
    }

    @PostMapping("/save")
    public String saveTea(@ModelAttribute Tea tea) {
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
}
