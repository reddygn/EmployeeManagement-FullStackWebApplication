package com.naveen.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.EmployeeManagement.entity.Assets;
import com.naveen.EmployeeManagement.service.AssetsService;

@RestController
@RequestMapping(value = "/api/assets")
public class AssetsController {
	
	@Autowired
	AssetsService assetsService;
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public List<Assets> getEmployeeAssets(@PathVariable Long id) {

		return assetsService.getEmployeeAssets(id);
	}
	

}
