package com.accolite.au.coursemanagement.services;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.repository.CourseMaterialRepository;
import com.accolite.au.coursemanagement.util.CourseMaterialHistoryRowMapper;
import com.accolite.au.coursemanagement.util.CourseMaterialRowMapper;
import com.accolite.au.coursemanagement.util.CourseRowMapper;

@Service
public class CourseMaterialService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	CourseMaterialRepository materialRepository;
	
	public CourseMaterial getCourseMaterial(int id) {
		try {
			return  materialRepository.getCourseMaterial(id);
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material id "+ id);
			return null;
		}
	}
	
	//returns all material for course with courseId
	public List<CourseMaterial> getCourseMaterialForCourse(int courseId) {
		
		try {
			return  materialRepository.getCourseMaterialForCourse(courseId);
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material for course "+ courseId);
			return null;
		}
	}
	
	public boolean createCourseMaterial(CourseMaterial cm) {
		//TODO: add created and updated by
		try {
			return materialRepository.createCourseMaterial(cm);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}

	}
	
	public boolean updateCourseMaterial(int id, CourseMaterial cm) {
		//TODO: add created and updated by
		
		try {
			return materialRepository.updateCourseMaterial(id, cm);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public boolean deleteCourseMaterial(int id) {
		try {
			return materialRepository.deleteCourseMaterial(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}

	
	public List<CourseMaterial> getHistory(int id){
		try {
			return  materialRepository.getHistory(id);
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material History for course-material "+ id);
			return null;
		}
	}
	
	
	public byte[] downloadLatest(int id) {
		return materialRepository.downloadLatest(id);
	}
	
	public byte[] downloadMaterial(int id, int version) {
		
		// Query history table first, if result is empty send lastest version from material table
		try {
			return  materialRepository.downloadMaterial(id, version);
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("file not found in history, returning the latest version");
			return this.downloadLatest(id);
		}
		
	}
}
