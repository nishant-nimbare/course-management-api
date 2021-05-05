package com.accolite.au.coursemanagement.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.util.CourseLocationRowMapper;

@Repository
public class CourseLocationRepository {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//get all
	public List<CourseLocation> getLocations(){
		try {
			String sql = "select id, name from location;";
			return (List<CourseLocation>)jdbcTemplate.query(sql, new CourseLocationRowMapper());
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	//get single
	public CourseLocation getLocation(int id) {
		try {
			String sql = "select id, name from location where id= ? ;";
			return (CourseLocation)jdbcTemplate.queryForObject(sql, new CourseLocationRowMapper(), new Object[] { id });
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	
	//create location
	public CourseLocation createLocation(String name) {
		String sql = "INSERT INTO location (name) VALUES ( ? );";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		try {
			jdbcTemplate.update( 
					connection -> {
						PreparedStatement ps= connection.prepareStatement(sql, new String[] {"id"});
						ps.setString(1, name);
						return ps;
					}, keyHolder);

//			int id = keyHolder.getKeyAs(Integer.class);
			
			Number idKey = keyHolder.getKey();
			
			int id = idKey.intValue();
			
			CourseLocation res = new CourseLocation();
			res.setId(id);
			res.setName(name);
			return res;
			
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
			
	}
	
	
	//update location
	public CourseLocation updateLocation(int id, String name) {
		
		String sql = "update location set name = ? where id = ? ;";
		try {
			int ra = jdbcTemplate.update(sql, name, id);
			
			if(ra == 1) {
				CourseLocation res = new CourseLocation();
				res.setId(id);
				res.setName(name);
				return res;
			}else 
				return null;
			
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	//delete location
	
	public boolean deleteLocation(int id) {
		String sql = "delete from location where id= ? ;";
		try {
			int ra = jdbcTemplate.update(sql, id);
			return (ra==1);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
}
