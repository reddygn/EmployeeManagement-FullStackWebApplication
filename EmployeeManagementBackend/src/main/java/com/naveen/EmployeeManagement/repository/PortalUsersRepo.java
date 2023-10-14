package com.naveen.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naveen.EmployeeManagement.entity.PortalUsers;

@Repository
public interface PortalUsersRepo extends JpaRepository<PortalUsers, Long>{

	PortalUsers findByUserName(String userName);

}
