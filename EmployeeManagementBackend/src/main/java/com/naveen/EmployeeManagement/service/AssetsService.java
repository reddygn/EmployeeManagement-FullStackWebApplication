package com.naveen.EmployeeManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.naveen.EmployeeManagement.entity.Assets;
import com.naveen.EmployeeManagement.repository.AssetsRepo;

@Service
public class AssetsService {

	@Autowired
	AssetsRepo assetsRepo;

	public List<Assets> getEmployeeAssets(Long employeeId) {

		List<Assets> assets = assetsRepo.findAllByEmployeeId(employeeId);
		return assets;
	}

	public ResponseEntity<Map<String, Boolean>> delAsset(Long id) {

		Assets asset = assetsRepo.findById(id).orElseThrow(() -> new RuntimeException());
		assetsRepo.delete(asset);

		Map<String, Boolean> response = new HashMap<>();

		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public Assets addAsset(Assets asset, Long id) {

		Assets a = new Assets();

		a.setAssetId(asset.getAssetId());
		a.setAssetName(asset.getAssetName());
		a.setEmployeeId(id);

		return assetsRepo.saveAndFlush(a);
	}

	public Assets updateAsset(Assets asset, Long id) {
		Assets assetResult = assetsRepo.findById(id).orElseThrow(() -> new RuntimeException());
		BeanUtils.copyProperties(asset, assetResult);
		assetsRepo.saveAndFlush(assetResult);
		return assetResult;
	}

	public ResponseEntity<Assets> getAssetById(Long id) {

		Assets asset = assetsRepo.findById(id).orElseThrow(() -> new RuntimeException());

		return ResponseEntity.ok(asset);
	}

}
