package com.naveen.EmployeeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.naveen.EmployeeManagement.entity.PortalUsers;
import com.naveen.EmployeeManagement.repository.PortalUsersRepo;

@Service
public class AdminService {

	@Autowired
	PortalUsersRepo portalUsersRepo;

	public ResponseEntity<PortalUsers> addPortalUser(PortalUsers user) {

		try {
			PortalUsers portalUser = portalUsersRepo.findByUserName(user.getUserName());

			if (portalUser == null) {

				String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
				user.setPassword(encodedPassword);

				portalUsersRepo.saveAndFlush(user);
				return ResponseEntity.ok(user);

			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserName already exists");

			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserName already exists", e);
		}

	}

}
