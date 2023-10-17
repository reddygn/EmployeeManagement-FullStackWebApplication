package com.naveen.EmployeeManagement.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveen.EmployeeManagement.entity.Assets;
import com.naveen.EmployeeManagement.entity.Employee;
import com.naveen.EmployeeManagement.repository.AssetsRepo;
import com.naveen.EmployeeManagement.repository.EmployeeRepo;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	AssetsRepo assetsRepo;

	public List<Employee> getAllEmployees() {

		// fetch all the employees
		// if no employees are present in the DB table, fetching it from JSON file
		// also add the employees from json file to the table in the DB.

		try {
			if (employeeRepo.findAll().size() == 0) {

				logger.info("Fetching Employees From the JSON File");

				List<Employee> employees = getAllEmployeesFromJsonFile();

				for (Employee employee : employees) {

					employeeRepo.saveAndFlush(employee);

				}

				logger.info("Savings Employees Into The DB Is Done");

				return employees;
			} else {

				logger.info("Fetching Employees From The Server / DB");

				return employeeRepo.findAll();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;

	}

	public ResponseEntity<Employee> getEmployeeById(Long id) {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException());

		return ResponseEntity.ok(employee);
	}

	public ResponseEntity<Map<String, Boolean>> delEmployee(Long id) {

		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException());
		List<Assets> assets = assetsRepo.findAllByEmployeeId(employee.getId());

		if (assets.size() > 0) {
			for (Assets asset : assets) {
				assetsRepo.delete(asset);
			}
		}

		employeeRepo.delete(employee);

		Map<String, Boolean> response = new HashMap<>();

		response.put("Deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

	public Employee createEmployee(Employee employee) {

		return employeeRepo.save(employee);

	}

	public Employee updateEmployee(Employee employee, Long id) {
		Employee employeeResult = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException());

		BeanUtils.copyProperties(employee, employeeResult);

		employeeRepo.saveAndFlush(employeeResult);

		return employeeResult;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployeesFromJsonFile() {

		List<Employee> employees = null;

		try {

			org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
			ClassLoader classLoader = getClass().getClassLoader();

			JSONObject jsonObject = (JSONObject) jsonParser
					.parse(new FileReader(classLoader.getResource("EmployeesTestData.json").getFile()));

			JSONArray productsJsonArray = (JSONArray) jsonObject.get("Employees");

			ObjectMapper mapper = new ObjectMapper();

			employees = mapper.readValue(productsJsonArray.toString(), new TypeReference<List<Employee>>() {
			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return employees;
	}
}
