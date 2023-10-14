package com.naveen.EmployeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.EmployeeManagement.entity.PortalUsers;
import com.naveen.EmployeeManagement.service.AdminService;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<PortalUsers> addPortalUser(@RequestBody PortalUsers user){

        return adminService.addPortalUser(user);
    }
	

}
