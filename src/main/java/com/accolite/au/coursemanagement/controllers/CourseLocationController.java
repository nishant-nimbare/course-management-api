package com.accolite.au.coursemanagement.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.services.CourseLocationService;

@RestController
@RequestMapping("/location")
public class CourseLocationController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Autowired
	CourseLocationService locationService;
	
	@GetMapping
	public List<CourseLocation> getAllLocation(){
		return locationService.getLocations();
	}

	@GetMapping("/{id}")
	public CourseLocation getlocation(@PathVariable("id") int id) {
		return locationService.getLocation(id);
	}
	
	@PostMapping
	public CourseLocation createLocation(@RequestParam(value="name", required=true)  String locName) {
		return locationService.createLocation(locName);
	}
	
	@PutMapping("/{id}")
	public CourseLocation updateLocation(@PathVariable("id") int id, @RequestParam(value="name", required=true)  String locName) {
		return locationService.updateLocation(id, locName);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable("id") int id) {
		locationService.deleteLocation(id);
	}
	
	
}
