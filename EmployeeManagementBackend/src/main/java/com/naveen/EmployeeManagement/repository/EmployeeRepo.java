package com.naveen.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveen.EmployeeManagement.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
