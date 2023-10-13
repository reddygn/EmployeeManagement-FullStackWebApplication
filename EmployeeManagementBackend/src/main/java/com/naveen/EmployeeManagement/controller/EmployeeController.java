package com.naveen.EmployeeManagement.controller;

import com.naveen.EmployeeManagement.model.Employee;
import com.naveen.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    // updateEmployee

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("")
    public List<Employee> getAllEmployees(){

        return employeeService.getAllEmployees();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

        return employeeService.getEmployeeById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delEmployee(@PathVariable Long id){

        return employeeService.delEmployee(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeService.createEmployee(employee);
    }



    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id){

        return employeeService.updateEmployee(employee, id);
    }




}
