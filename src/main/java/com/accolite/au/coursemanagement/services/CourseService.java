package com.accolite.au.coursemanagement.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.repository.CourseRepository;
import com.accolite.au.coursemanagement.repository.UserRepository;
import com.accolite.au.coursemanagement.util.CourseRowMapper;
import com.accolite.au.coursemanagement.util.UserCourseResponse;

import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class CourseService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserRepository userRepo;
	
	public List<Course> getAllCourses(){
		return courseRepository.getAllCourses();
	}
	
	public List<Course> getAllCourses(String search){
		if(search==null || search.equals("")) {
			return this.getAllCourses();
		}
		return courseRepository.searchCourses(search.trim());
	}
	
	public List<UserCourseResponse> getStudentCourses(String studentId, String search){
		
		List<Course> allCourses = this.getAllCourses(search);
		//List<Integer> enrolledCourses = userRepo.getEnrolledCourses(studentId);
		
		Set<Integer> enrolledCourses  = new HashSet<Integer>(userRepo.getEnrolledCourses(studentId));
		
		LOGGER.info("user enrolled courses "+ enrolledCourses);
		
		List<UserCourseResponse> res = new ArrayList<>(allCourses.size());

		for(Course c : allCourses) {
			UserCourseResponse n = UserCourseResponse.fromCourse(c); 
			
			if(enrolledCourses.contains(c.getId())) {
				n.setEnrolled(true);
			}
			res.add(n);
		}
		return res;
	}
	
	
	public Course getCourse(int id) {
		try {
			return courseRepository.getCourse(id);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public UserCourseResponse getCourseStudent(int courseId, String userId) {
		try {
			Course c = courseRepository.getCourse(courseId);
			UserCourseResponse res = UserCourseResponse .fromCourse(c);
			if(userRepo.isEnrolled(userId, courseId)) {
				res.setEnrolled(true);
			}
			return res;
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public boolean createCourse(Course c) {
		//TODO: add created and updated by
		try {
			return courseRepository.createCourse(c);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
		
	}
	
	public boolean updateCourse(int id, Course c) {
		//TODO: add created and updated by
		
		try {
			return courseRepository.updateCourse(id, c);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public boolean deleteCourse(int id) {
		try {
			return courseRepository.deleteCourse(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public List<String> skillsOfCourse(int id){
		try {
			return courseRepository.skillsOfCourse(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean addSkill(int courseId, String skill) {
		try {
			return courseRepository.addSkill(courseId, skill);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean removeSkill(int courseId, String skill) {
		try {
			return courseRepository.removeSkill(courseId, skill);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public List<String> prerequisitesOfCourse(int id){
		try {
			return courseRepository.prerequisitesOfCourse(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean addPrerequisite(int courseId, String prerequisite) {
		try {
			return courseRepository.addPrerequisite(courseId, prerequisite);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean removeprerequisite(int courseId, String prerequisite) {
		try {
			return courseRepository.removeprerequisite(courseId, prerequisite);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	
	
}
