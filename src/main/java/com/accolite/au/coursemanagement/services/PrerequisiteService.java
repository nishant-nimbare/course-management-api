package com.accolite.au.coursemanagement.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrerequisiteService {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<String> prerequisitesOfCourse(int id){
		try {
			String sql = "select prerequisite from course_prerequisites where course_id= ? ;";
			return (List<String>)jdbcTemplate.queryForList(sql, String.class, new Object[] { id } );
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean addPrerequisite(int courseId, String prerequisite) {
		try {
			String sql = "insert into course_prerequisites values ( ?, ?);";
			int ra = jdbcTemplate.update(sql, courseId, prerequisite);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean removeprerequisite(int courseId, String prerequisite) {
		try {
			String sql = "delete from course_prerequisites where course_id= ? and  prerequisite= ? ;";
			int ra = jdbcTemplate.update(sql, courseId, prerequisite);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
}
