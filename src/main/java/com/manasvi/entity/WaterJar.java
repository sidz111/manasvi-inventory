package com.manasvi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WaterJar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String jarOwner;
	
	private Integer totalJars;
	
	private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJarOwner() {
		return jarOwner;
	}

	public void setJarOwner(String jarOwner) {
		this.jarOwner = jarOwner;
	}

	public Integer getTotalJars() {
		return totalJars;
	}

	public void setTotalJars(Integer totalJars) {
		this.totalJars = totalJars;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

		
}
