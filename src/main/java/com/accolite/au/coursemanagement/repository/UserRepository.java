package com.accolite.au.coursemanagement.repository;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.util.UserRowMapper;

@Repository
public class UserRepository {


	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public User getUser(String email) {
		String sql = "SELECT email, name, role, created_at  FROM user where user.email = ? ;";
		try {
			return  (User)jdbcTemplate.queryForObject(sql, new UserRowMapper(), new Object[] { email });
		}catch(EmptyResultDataAccessException e) {
			return null;
		}	
	}
	
	public boolean createUser(User u) {
		//TODO: add created and updated by
		try {
			
			String sql = "INSERT INTO user (email, name) VALUES ( ?, ?);";
			int ra = jdbcTemplate.update(sql, u.getEmail(), u.getName());
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}

	}
	
	public boolean enroll(String userId, int courseId) {
		try {
			String sql = "INSERT INTO enrollment (user, course_id) VALUES ( ?, ?);";
			LOGGER.info(sql + " "+ userId+" "+ courseId);
			int ra = jdbcTemplate.update(sql, userId, courseId);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public List<Integer> getEnrolledCourses(String userId){
	
		try {
			String sql = "select course_id from enrollment where user= ? ;";
			return (List<Integer>)jdbcTemplate.queryForList(sql, Integer.class, new Object[] { userId } );
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	public boolean isEnrolled(String userId, int courseId) {
		try {
			String sql = "SELECT EXISTS(SELECT * FROM enrollment WHERE user=? AND course_id=? )";
			LOGGER.info(sql + " "+ userId+" "+ courseId);
			int ra = jdbcTemplate.queryForObject(sql, Integer.class, userId, courseId);
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
}
