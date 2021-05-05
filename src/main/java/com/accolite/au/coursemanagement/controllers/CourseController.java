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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.models.UserRole;
import com.accolite.au.coursemanagement.services.CourseMaterialService;
import com.accolite.au.coursemanagement.services.CourseService;
import com.accolite.au.coursemanagement.services.PrerequisiteService;
import com.accolite.au.coursemanagement.services.SkillsService;
import com.accolite.au.coursemanagement.util.UserCourseResponse;

@RestController
@RequestMapping("/course")
public class CourseController {
//	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	CourseService courseService;
//	SkillsService skillService;
//	PrerequisiteService prerequisiteService;
	
	@Autowired
	public CourseController(
			CourseService courseService//,
//			SkillsService skillService,
//			PrerequisiteService prerequisiteService
			) {
		super();
		this.courseService = courseService;
//		this.skillService = skillService;
//		this.prerequisiteService = prerequisiteService;
	}

	@GetMapping
	public List<UserCourseResponse> getAllCourses(@RequestHeader(value="userId", required=false) String userId, @RequestHeader(value="role", required=false) String role, @RequestParam(value="search", required=false) String search){
		
		if(role != null)
		switch(UserRole.valueOf(role)) {
		case STUDENT:
			return courseService.getStudentCourses(userId, search);
		default:
			break;
		}			
		
		return UserCourseResponse.fromCourse(  
				courseService.getAllCourses(search)
			);
		
	}

	@GetMapping("/{id}")
	public UserCourseResponse getCourse(@PathVariable("id") int id, @RequestHeader(value="userId", required=false) String userId, @RequestHeader(value="role", required=false) String role) {

		if(role != null && UserRole.valueOf(role)==UserRole.STUDENT ) {
			return courseService.getCourseStudent(id, userId);
		}
		return UserCourseResponse.fromCourse(courseService.getCourse(id));
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
		return courseService.skillsOfCourse(id);
	}
	
	@PostMapping("/{id}/skills")
	public void addSkill(@PathVariable("id") int id, @RequestBody List<String> skills) {
		for(String s:skills) {
			courseService.addSkill(id, s);
		}
	}
	
	@DeleteMapping("/{id}/skills/{skill}")
	public void removeSkill(@PathVariable("id") int id, @PathVariable("skill") String skill) {
		courseService.removeSkill(id, skill);
	}
	
	
	//prerequisites
	@GetMapping("/{id}/prerequisites")
	public List<String> getCoursePrerequisites(@PathVariable("id") int id) {
		return courseService.prerequisitesOfCourse(id);
	}
	
	@PostMapping("/{id}/prerequisites")
	public void addPrerequisites(@PathVariable("id") int id, @RequestBody List<String> prerequisites) {
		for(String p:prerequisites) {
			courseService.addPrerequisite(id, p);
		}
	}
	
	@DeleteMapping("/{id}/prerequisites/{prerequisite}")
	public void removePrerequisites(@PathVariable("id") int id, @PathVariable("prerequisite") String prerequisite) {
		courseService.removeprerequisite(id, prerequisite);
	}
	
	
}
