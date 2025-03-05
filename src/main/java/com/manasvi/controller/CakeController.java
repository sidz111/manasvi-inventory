package com.manasvi.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manasvi.entity.Cake;
import com.manasvi.service.CakeService;

@Controller
@RequestMapping("/cakes")
public class CakeController {
	
	@Autowired
	CakeService cakeService;
	
	@GetMapping("/list")
	public String cakeList(Model model) {
		model.addAttribute("cakes", cakeService.getAllCakes());
		model.addAttribute("cake", new Cake());
		return "cake/cake-list";
	}
	
	@PostMapping("/add")
	public String addCake(@ModelAttribute Cake cake) {
		cake.setDate(new Date().toString());
		cakeService.addCake(cake);
		return "redirect:/cakes/list";
	}

}
