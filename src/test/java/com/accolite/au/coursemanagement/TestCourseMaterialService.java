package com.accolite.au.coursemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.repository.CourseMaterialRepository;
import com.accolite.au.coursemanagement.services.CourseMaterialService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestCourseMaterialService {

	@Mock
	CourseMaterialRepository materialRepository;
	
	@InjectMocks
	CourseMaterialService materialService;
	
	@Test
	void getCourseMaterial() {
		CourseMaterial expected = new CourseMaterial(1, 1, 1, "test", "test", null);
		given(materialRepository.getCourseMaterial(1)).willReturn(expected);
		
		CourseMaterial actual = materialService.getCourseMaterial(1);
		assertEquals(expected, actual);
		
		verify(materialRepository).getCourseMaterial(1);
	}


	@Test
	void getCourseMaterialForCourse() {
		List<CourseMaterial> expected = Arrays.asList(new CourseMaterial(1, 1, 1, "test", "test", null));
		given(materialRepository.getCourseMaterialForCourse(1)).willReturn(expected);
		
		List<CourseMaterial> actual = materialService.getCourseMaterialForCourse(1);
		assertEquals(expected, actual);
		
		verify(materialRepository).getCourseMaterialForCourse(1);

	}

	@Test
	void createCourseMaterial() {
		CourseMaterial cm = new CourseMaterial(1, 1, 1, "test", "test", null);
		given(materialRepository.createCourseMaterial(cm)).willReturn(true);
		assertTrue(materialService.createCourseMaterial(cm));
		verify(materialRepository).createCourseMaterial(cm);
	}

	@Test
	void updateCourseMaterial() {
		CourseMaterial cm = new CourseMaterial(1, 1, 1, "test", "test", null);
		given(materialRepository.updateCourseMaterial(1,cm)).willReturn(true);
		assertTrue(materialService.updateCourseMaterial(1,cm));
		verify(materialRepository).updateCourseMaterial(1,cm);
		
	}

	@Test
	void deleteCourseMaterial() {
		given(materialRepository.deleteCourseMaterial(1)).willReturn(true);
		assertTrue(materialService.deleteCourseMaterial(1));
		verify(materialRepository).deleteCourseMaterial(1);
	}

	@Test
	void getHistory() {
		List<CourseMaterial> expected = Arrays.asList(new CourseMaterial(1, 1, 1, "test", "test", null));
		given(materialRepository.getHistory(1)).willReturn(expected);
		
		List<CourseMaterial> actual = materialService.getHistory(1);
		assertEquals(expected, actual);
		
		verify(materialRepository).getHistory(1);
	}

	@Test
	void downloadLatest() {
		byte[] expected = "test".getBytes();
		given(materialRepository.downloadLatest(1)).willReturn(expected);
		assertEquals(expected, materialService.downloadLatest(1));
		verify(materialRepository).downloadLatest(1);
		
	}

	@Test
	void downloadMaterial() {
		byte[] expected = "test".getBytes();
		given(materialRepository.downloadMaterial(1,1)).willReturn(expected);
		assertEquals(expected, materialService.downloadMaterial(1,1));
		verify(materialRepository).downloadMaterial(1,1);
	}
}
