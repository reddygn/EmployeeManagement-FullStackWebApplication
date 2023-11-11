package com.naveen.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naveen.EmployeeManagement.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	
	@Query(value = "SELECT * FROM Employee u WHERE u.email_id = ?1", nativeQuery = true)
	Employee findByEmailId(String emailId);

}

