package com.naveen.EmployeeManagement.service;


import com.naveen.EmployeeManagement.model.Employee;
import com.naveen.EmployeeManagement.repository.EmployeeRepo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    public List<Employee> getAllEmployees() {

        return employeeRepo.findAll();
    }

    public ResponseEntity<Employee> getEmployeeById(Long id) {

        Employee employee = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException());

            return ResponseEntity.ok(employee);
    }

    public ResponseEntity<Map<String, Boolean>>  delEmployee(Long id) {

        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException());

        employeeRepo.delete(employee);

        Map<String, Boolean> response = new HashMap<>();

        response.put("Deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    public Employee createEmployee(Employee employee) {

        employeeRepo.save(employee);

        return   employeeRepo.save(employee);

    }

	public Employee updateEmployee(Employee employee, Long id) {
         Employee employeeResult = employeeRepo.findById(id).orElseThrow(()-> new RuntimeException());
         
         BeanUtils.copyProperties(employee, employeeResult);
         
         employeeRepo.saveAndFlush(employeeResult);
		
		return employeeResult;
	}
}
