package com.naveen.EmployeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naveen.EmployeeManagement.entity.Employee;
import com.naveen.EmployeeManagement.repository.EmployeeRepo;

@SpringBootTest
public class EmployeeaServiceTests {

	@MockBean
	EmployeeRepo employeeRepo;

	@Test
	public void testGetEmployeeById() {

		Optional<Employee> employee = Optional.of(new Employee());

		employee.get().setEmailId("test@gm.com");
		employee.get().setFirstName("Nav");
		employee.get().setLastName("Red");
		employee.get().setId(1L);

		System.out.println(employee);
		Mockito.when(employeeRepo.findById(1L)).thenReturn(employee);

		assertEquals(employee.get().getEmailId(), "test@gm.com");
		assertNotEquals(employee.get().getLastName(), "abc");

	}

	@Test
	public void testCreateEmployee() {

		Employee employee = new Employee();

		employee.setEmailId("test@gm.com");
		employee.setFirstName("Nav");
		employee.setLastName("Red");
		employee.setId(1L);

		Mockito.when(employeeRepo.saveAndFlush(employee)).thenReturn(employee);

		Employee returnedEmployee = employeeRepo.saveAndFlush(employee);

		assertEquals(1L, returnedEmployee.getId());
		assertEquals("Nav", returnedEmployee.getFirstName());
		assertNotEquals("rer", returnedEmployee.getLastName());

	}

}
