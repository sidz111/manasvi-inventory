	package com.manasvi.controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	
	import com.manasvi.service.StockItemService;
	import com.manasvi.service.TeaService;
	import com.manasvi.service.WaterJarService;
	
	@Controller
	public class HomeController {
		
		@Autowired
		StockItemService stockItemService;
		
		@Autowired
		TeaService teaService;
		
		@Autowired
		WaterJarService waterJarService;
		
	    @GetMapping("/")
	    public String home(Model model) {
			model.addAttribute("totalStocks", stockItemService.getAllStockItems());
	    	model.addAttribute("totalTeas", teaService.getAllTeaRecords().size());
	    	model.addAttribute("totalWaterJars", waterJarService.getAllWaterJars().size());
	        return "index";
	    }
	}
