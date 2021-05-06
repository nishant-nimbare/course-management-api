package com.accolite.au.coursemanagement.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.repository.CourseMaterialRepository;

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
		
			return materialRepository.createCourseMaterial(cm);

	}
	
	public boolean updateCourseMaterial(int id, CourseMaterial cm) {

			return materialRepository.updateCourseMaterial(id, cm);

	}
	
	public boolean deleteCourseMaterial(int id) {

			return materialRepository.deleteCourseMaterial(id);

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
