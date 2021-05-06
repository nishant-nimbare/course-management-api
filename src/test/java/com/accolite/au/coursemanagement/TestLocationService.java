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

import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.repository.CourseLocationRepository;
import com.accolite.au.coursemanagement.services.CourseLocationService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestLocationService {
	
	@Mock
	CourseLocationRepository locationRepository;
	
	@InjectMocks
	CourseLocationService locationService;
	
	@Test
	void getLocations() {
		List<CourseLocation> expected = Arrays.asList(new CourseLocation(1,"test"), new CourseLocation(2,"test2"));
		given(locationRepository.getLocations()).willReturn(expected);
		
		List<CourseLocation> actual = locationService.getLocations();
		assertEquals(expected, actual);
		
		verify(locationRepository).getLocations();
	}

	@Test
	void getLocation() {
		CourseLocation expected = new CourseLocation(1,"test");
		given(locationRepository.getLocation(1)).willReturn(expected);
		
		CourseLocation actual = locationService.getLocation(1);
		assertEquals(expected, actual);
		
		verify(locationRepository).getLocation(1);
	}
	
	@Test
	void createLocation() {
		CourseLocation expected = new CourseLocation(1,"new location");
		given(locationRepository.createLocation("new location")).willReturn(expected);
		CourseLocation actual = locationService.createLocation("new location");
		assertEquals(expected, actual);
		
		verify(locationRepository).createLocation("new location");
	}
	
	@Test
	void updateLocation() {
		CourseLocation expected = new CourseLocation(1,"updated location");
		given(locationRepository.updateLocation(1,"updated location")).willReturn(expected);
		CourseLocation actual = locationService.updateLocation(1,"updated location");
		assertEquals(expected, actual);
		
		verify(locationRepository).updateLocation(1,"updated location");
	}
	
	@Test
	void deleteLocation() {
		given(locationRepository.deleteLocation(1)).willReturn(true);
		assertTrue(locationService.deleteLocation(1));
		verify(locationRepository).deleteLocation(1);
	}
}
