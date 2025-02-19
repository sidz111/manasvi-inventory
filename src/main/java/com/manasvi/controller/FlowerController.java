package com.manasvi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flower")
public class FlowerController {

	@GetMapping("/list")
	public String getFlowerPage() {
		return "flower/flower-list";
	}
	
}
