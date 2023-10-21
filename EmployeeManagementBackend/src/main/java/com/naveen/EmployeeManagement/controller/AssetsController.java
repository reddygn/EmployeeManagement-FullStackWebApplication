package com.naveen.EmployeeManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("/{id}/all")
	public List<Assets> getEmployeeAssets(@PathVariable Long id) {

		return assetsService.getEmployeeAssets(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public ResponseEntity<Assets> getAssetById(@PathVariable Long id) {

		return assetsService.getAssetById(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> delEmployee(@PathVariable Long id) {

		return assetsService.delAsset(id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/{id}")
	public Assets addAsset(@RequestBody Assets asset, @PathVariable Long id) {

		return assetsService.addAsset(asset, id);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/{id}")
	public Assets updateAsset(@RequestBody Assets asset, @PathVariable Long id) {

		return assetsService.updateAsset(asset, id);
	}

}
