package com.naveen.EmployeeManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naveen.EmployeeManagement.entity.Assets;
import com.naveen.EmployeeManagement.repository.AssetsRepo;

@SpringBootTest
public class AssetsServiceTests {

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

		assertNotEquals(a.get().getAssetId(), "BBC");
		assertNotEquals(a.get().getAssetName(), "LLC");

	}

	@Test
	public void testGetEmployeeAssets() {

		List<Assets> assets = new ArrayList<Assets>();

		Assets a1 = new Assets();

		a1.setAssetId("ABC");
		a1.setAssetName("QWERTY");
		a1.setEmployeeId(1L);

		assets.add(a1);

		Assets a2 = new Assets();
		a2.setAssetId("CBA");
		a2.setAssetName("QWERTY");
		a2.setEmployeeId(1L);

		assets.add(a2);

		assertEquals(assets.size(), 2);
		assertNotEquals(assets.size(), 4);
	}

	@Test
	public void testAddAsset() {

		Assets a = new Assets();

		a.setAssetId("ABC");
		a.setAssetName("QWERTY");
		a.setEmployeeId(1L);

		Mockito.when(assetsRepo.saveAndFlush(a)).thenReturn(a);

		Assets returnedAsset = assetsRepo.saveAndFlush(a);

		assertEquals("ABC", returnedAsset.getAssetId());
		assertEquals("QWERTY", returnedAsset.getAssetName());

	}

	@Test
	public void testUpdateAsset() {

		Assets a = new Assets();
		a.setAssetName("QWERTY");

		Assets a1 = new Assets();

		a1.setAssetId("AAA");
		a1.setAssetName("YTREWQ");
		a1.setEmployeeId(1L);

		a1.setAssetName(a.getAssetName());

		Mockito.when(assetsRepo.saveAndFlush(a1)).thenReturn(a1);

		Assets returnedAsset = assetsRepo.saveAndFlush(a1);

		assertEquals("QWERTY", returnedAsset.getAssetName());

	}
}
