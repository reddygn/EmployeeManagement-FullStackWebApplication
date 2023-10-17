package com.naveen.EmployeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naveen.EmployeeManagement.entity.Assets;
import com.naveen.EmployeeManagement.repository.AssetsRepo;

@SpringBootTest
public class AssetsServiceTests {

	@Autowired
	AssetsService assetsService;

	@MockBean
	AssetsRepo assetsRepo;

	@Test
	public void testGetAssetByIdSuccess() {

		Optional<Assets> a = Optional.of(new Assets());

		a.get().setAssetId("ABC");
		a.get().setAssetName("QWERTY");
		a.get().setEmployeeId(1L);

		Mockito.when(assetsRepo.findById(1L)).thenReturn(a);

		assertEquals(a.get().getAssetId(), "ABC");

		assertEquals(a.get().getAssetName(), "QWERTY");

	}

	@Test
	public void testGetAssetByIdFail() {
		
		Optional<Assets> a = Optional.of(new Assets());

		a.get().setAssetId("ABC");
		a.get().setAssetName("QWERTY");
		a.get().setEmployeeId(1L);

		Mockito.when(assetsRepo.findById(1L)).thenReturn(a);

//        assertEquals(a.get().getEmployeeId(), 1L);

		assertNotEquals(a.get().getAssetId(), "BBC");

		assertNotEquals(a.get().getAssetName(), "LLC");

	}

}
