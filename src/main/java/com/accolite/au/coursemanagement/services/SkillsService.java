package com.accolite.au.coursemanagement.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SkillsService {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<String> skillsOfCourse(int id){
		try {
			String sql = "select skill from course_skills where course_id= ? ;";
			return (List<String>)jdbcTemplate.queryForList(sql, String.class, new Object[] { id } );
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean addSkill(int courseId, String skill) {
		try {
			String sql = "insert into course_skills values ( ?, ?);";
			int ra = jdbcTemplate.update(sql, courseId, skill);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
	
	public boolean removeSkill(int courseId, String skill) {
		try {
			String sql = "delete from course_skills where course_id= ? and  skill= ? ;";
			int ra = jdbcTemplate.update(sql, courseId, skill);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			throw e;
		}
	}
}
