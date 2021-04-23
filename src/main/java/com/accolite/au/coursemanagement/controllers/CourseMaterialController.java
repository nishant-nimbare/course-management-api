package com.accolite.au.coursemanagement.controllers;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.services.CourseMaterialService;

@RestController
@RequestMapping("/material")
public class CourseMaterialController {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	CourseMaterialService cmService;
	
	//get course material for course 
	@GetMapping("/")
	public List<CourseMaterial> getMaterialForCourse(@RequestParam(value="courseId", required=true) int courseId) {
		return cmService.getCourseMaterialForCourse(courseId);
	}
	
	
	@GetMapping("/{id}")
	public CourseMaterial getMaterial(@PathVariable("id") int id) {
		return cmService.getCourseMaterial(id);
	}
	
	@PostMapping("/")
	public void createMaterial( @RequestParam("courseId") int courseId ,@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("file") MultipartFile file) {
		byte[] fileBytes;
		
		try {
			fileBytes = file.getBytes();
		}catch(IOException e) {
			LOGGER.warning("file upload failed "+ e.getMessage());
			return;
		}
		
		CourseMaterial cm = new CourseMaterial();
		cm.setCourse_id(courseId);
		cm.setName(name);
		cm.setDescription(description);
		cm.setFile(fileBytes);
		cmService.createCourseMaterial(cm);
	}
	
	//form data
	@PutMapping("/{id}")
	public void updateMaterial(@PathVariable("id") int id, @RequestParam(value="courseId", required=false) int courseId ,@RequestParam(value="name", required=false) String name, @RequestParam(value="description", required=false) String description, @RequestParam(value="file", required=false) MultipartFile file) {
byte[] fileBytes;
		
		try {
			fileBytes = file.getBytes();
		}catch(IOException e) {
			LOGGER.warning("file upload failed "+ e.getMessage());
			return;
		}
		
		CourseMaterial cm = new CourseMaterial();
		cm.setCourse_id(courseId);
		cm.setName(name);
		cm.setDescription(description);
		cm.setFile(fileBytes);
		cmService.updateCourseMaterial(id, cm);		
	}
	
	@DeleteMapping("/{id}")
	public void deleteMaterial(@PathVariable("id") int id) {
		cmService.deleteCourseMaterial(id);
	}
	
}
