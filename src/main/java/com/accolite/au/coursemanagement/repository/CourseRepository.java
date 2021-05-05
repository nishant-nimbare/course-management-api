package com.accolite.au.coursemanagement.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.util.CourseRowMapper;

@Repository
public class CourseRepository {


	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Course> getAllCourses(){
		String sql = "select id, name, description, location_id, created_at, created_by, updated_at, updated_by  from course;";
		return (List<Course>) jdbcTemplate.query(sql,new CourseRowMapper());
	}
	
	public List<Course> searchCourses(String search){
		if(search==null || search.equals("")) return this.getAllCourses();
		
		String sql = "select id, name, description, location_id, created_at, created_by, updated_at, updated_by from course where name LIKE ? ;";
		return (List<Course>) jdbcTemplate.query(sql,new CourseRowMapper(), new Object[] {"%"+search+"%"} );
	}
	
	
	public Course getCourse(int id) {
		String sql = "SELECT c.id as id, c.name as name, description, location_id, created_at, created_by, updated_at, updated_by, location.name   FROM course as c left join location on c.location_id=location.id where c.id = ? ;";
		try {
			return  (Course)jdbcTemplate.queryForObject(sql, new CourseRowMapper(), new Object[] { id });
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	
	public boolean createCourse(Course c) {
		//TODO: add created and updated by
		try {
			
			String sql = "INSERT INTO course (name, description, location_id) VALUES ( ?, ?, ?);";
			int ra = jdbcTemplate.update(sql, c.getName(), c.getDescription(), c.getCourseLocation().getId());
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
		
	}
	
	public boolean updateCourse(int id, Course c) {
		//TODO: add created and updated by
		
		try {
			StringBuilder sqlb = new StringBuilder(" update course set ");
			
			ArrayList<String> updates = new ArrayList<>(3);
			
			if(c.getName() != null && !c.getName().equals("")) {
				updates.add( new StringBuilder().append(" name='").append(c.getName()).append("' ").toString() );
			}
			
			if(c.getDescription() != null && !c.getDescription().equals("")) {
				updates.add( new StringBuilder().append(" description='").append(c.getDescription()).append("' ").toString() );
			}
			
			if(c.getCourseLocation().getId() != 0) {
				updates.add( new StringBuilder().append(" location_id=").append(c.getCourseLocation().getId()).append(" ").toString() );
			}

			sqlb.append(updates.stream().collect(Collectors.joining(",")));
			
			sqlb.append(" where id=").append(id).append(";");
			
			LOGGER.info("update query : "+ sqlb.toString());
			int ra = jdbcTemplate.update(sqlb.toString());
			
			return (ra==1);
		
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public boolean deleteCourse(int id) {
		try {
			String sql = "delete from course where id= ? ;";
			
			int ra = jdbcTemplate.update(sql, id);
			
			return (ra==1);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}
	
	public List<String> skillsOfCourse(int id){
		try {
			String sql = "select skill from course_skills where course_id= ? ;";
			return (List<String>)jdbcTemplate.queryForList(sql, String.class, new Object[] { id } );
		}catch(EmptyResultDataAccessException e) {
			return null;
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
	
	public List<String> prerequisitesOfCourse(int id){
		try {
			String sql = "select prerequisite from course_prerequisites where course_id= ? ;";
			return (List<String>)jdbcTemplate.queryForList(sql, String.class, new Object[] { id } );
		}catch(EmptyResultDataAccessException e) {
			return null;
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
