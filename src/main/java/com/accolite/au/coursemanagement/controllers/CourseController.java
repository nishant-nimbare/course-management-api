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
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.services.CourseMaterialService;
import com.accolite.au.coursemanagement.services.CourseService;
import com.accolite.au.coursemanagement.services.PrerequisiteService;
import com.accolite.au.coursemanagement.services.SkillsService;

@RestController
@RequestMapping("/course")
public class CourseController {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	CourseService courseService;
	SkillsService skillService;
	PrerequisiteService prerequisiteService;
	
	@Autowired
	public CourseController(CourseService courseService, SkillsService skillService,
			PrerequisiteService prerequisiteService) {
		super();
		this.courseService = courseService;
		this.skillService = skillService;
		this.prerequisiteService = prerequisiteService;
	}

	@GetMapping
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}

	@GetMapping("/{id}")
	public Course getCourse(@PathVariable("id") int id) {
		return courseService.getCourse(id);
	}
	
	@PostMapping
	public void createCourse(@RequestBody Course c) {
		courseService.createCourse(c);
	}
	
	@PutMapping("/{id}")
	public void updateCourse(@PathVariable("id") int id, @RequestBody Course c) {
		courseService.updateCourse(id, c);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable("id") int id) {
		courseService.deleteCourse(id);
	}
	
	
	@RequestMapping("/ping")
	public String ping() {
		return "pong";
	}
	
	
	// skills
	@GetMapping("/{id}/skills")
	public List<String> getCourseSkills(@PathVariable("id") int id) {
		return skillService.skillsOfCourse(id);
	}
	
	@PostMapping("/{id}/skills")
	public void addSkill(@PathVariable("id") int id, @RequestBody List<String> skills) {
		for(String s:skills) {
			skillService.addSkill(id, s);
		}
	}
	
	@DeleteMapping("/{id}/skills/{skill}")
	public void removeSkill(@PathVariable("id") int id, @PathVariable("skill") String skill) {
		skillService.removeSkill(id, skill);
	}
	
	
	//prerequisites
	@GetMapping("/{id}/prerequisites")
	public List<String> getCoursePrerequisites(@PathVariable("id") int id) {
		return prerequisiteService.prerequisitesOfCourse(id);
	}
	
	@PostMapping("/{id}/prerequisites")
	public void addPrerequisites(@PathVariable("id") int id, @RequestBody List<String> prerequisites) {
		for(String p:prerequisites) {
			prerequisiteService.addPrerequisite(id, p);
		}
	}
	
	@DeleteMapping("/{id}/prerequisites/{prerequisite}")
	public void removePrerequisites(@PathVariable("id") int id, @PathVariable("prerequisite") String prerequisite) {
		prerequisiteService.removeprerequisite(id, prerequisite);
	}
	
	
}
