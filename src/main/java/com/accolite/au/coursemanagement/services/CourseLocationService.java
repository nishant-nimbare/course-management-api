package com.accolite.au.coursemanagement.services;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.repository.CourseLocationRepository;
import com.accolite.au.coursemanagement.util.CourseLocationRowMapper;

@Service
public class CourseLocationService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	CourseLocationRepository locationRepository;

	//get all
	public List<CourseLocation> getLocations(){
		try {
			
			return locationRepository.getLocations();
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	//get single
	public CourseLocation getLocation(int id) {
		try {
			return locationRepository.getLocation(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	
	//create location
	public CourseLocation createLocation(String name) {
		try {
			return locationRepository.createLocation(name);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
			
	}
	
	
	//update location
	public CourseLocation updateLocation(int id, String name) {
		
		try {
			return locationRepository.updateLocation(id, name);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	//delete location
	
	public boolean deleteLocation(int id) {
		try {
			return locationRepository.deleteLocation(id);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
}
