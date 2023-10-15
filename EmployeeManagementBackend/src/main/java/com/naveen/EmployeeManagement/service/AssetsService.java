package com.naveen.EmployeeManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

}
