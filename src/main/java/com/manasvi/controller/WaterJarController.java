package com.manasvi.controller;

import com.manasvi.entity.WaterJar;
import com.manasvi.service.WaterJarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/waterjar")
public class WaterJarController {

    @Autowired
    private WaterJarService waterJarService;

    @GetMapping
    public String listWaterJars(Model model) {
        List<WaterJar> waterJars = waterJarService.getAllWaterJars();
        model.addAttribute("waterJars", waterJars);
        return "waterjar/list-waterjar";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("waterJar", new WaterJar());
        return "waterjar/add-waterjar";
    }

    @PostMapping("/save")
    public String saveWaterJar(@ModelAttribute WaterJar waterJar) {
        waterJarService.addWaterJar(waterJar);
        return "redirect:/waterjar";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<WaterJar> waterJar = waterJarService.getWaterJarById(id);
        model.addAttribute("waterJar", waterJar);
        return "waterjar/edit-waterjar";
    }

    @PostMapping("/update/{id}")
    public String updateWaterJar(@PathVariable Long id, @ModelAttribute WaterJar waterJar) {
        waterJarService.updateWaterJar(id, waterJar);
        return "redirect:/waterjar";
    }

    @GetMapping("/delete/{id}")
    public String deleteWaterJar(@PathVariable Long id) {
        waterJarService.deleteWaterJar(id);
        return "redirect:/waterjar";
    }
}
