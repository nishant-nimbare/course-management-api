package com.accolite.au.coursemanagement.services;

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
import com.accolite.au.coursemanagement.util.CourseMaterialHistoryRowMapper;
import com.accolite.au.coursemanagement.util.CourseMaterialRowMapper;
import com.accolite.au.coursemanagement.util.CourseRowMapper;

@Service
public class CourseMaterialService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public CourseMaterial getCourseMaterial(int id) {
		String sql = "select id, version, course_id, name, description, file, created_at, created_by, updated_at, updated_by from course_material where id= ? ;";
		try {
			return  (CourseMaterial)jdbcTemplate.queryForObject(sql, new CourseMaterialRowMapper(), new Object[] { id });
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material id "+ id);
			return null;
		}
	}
	
	//returns all material for course with courseId
	public List<CourseMaterial> getCourseMaterialForCourse(int courseId) {
		String sql = "select id, version, course_id, name, description, file, created_at, created_by, updated_at, updated_by from course_material where course_id= ? ;";
		try {
			return  (List<CourseMaterial>)jdbcTemplate.query(sql, new CourseMaterialRowMapper(), new Object[] { courseId });
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material for course "+ courseId);
			return null;
		}
	}
	
	public boolean createCourseMaterial(CourseMaterial cm) {
		//TODO: add created and updated by
		try {
			
			String sql = "INSERT INTO course_material (course_id, name, description, file) VALUES ( ?, ?, ?, ?);";
			int ra = jdbcTemplate.update(sql, cm.getCourse_id(), cm.getName(), cm.getDescription(), cm.getFile());
			return (ra==1);

		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}

	}
	
	public boolean updateCourseMaterial(int id, CourseMaterial cm) {
		//TODO: add created and updated by
		
		try {
			StringBuilder sqlb = new StringBuilder(" update course_material set ");
			
			ArrayList<String> updates = new ArrayList<>(3);
			
			if(cm.getName() != null && !cm.getName().equals("")) {
				updates.add( new StringBuilder().append(" name='").append(cm.getName()).append("' ").toString() );
			}
			
			if(cm.getDescription() != null && !cm.getDescription().equals("")) {
				updates.add( new StringBuilder().append(" description='").append(cm.getDescription()).append("' ").toString() );
			}
			
			if(cm.getFile() != null) {
				updates.add( new StringBuilder().append(" file=").append(cm.getFile()).append(" ").toString() );
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
	
	public boolean deleteCourseMaterial(int id) {
		try {
			String sql = "delete from course_material where id= ? ;";
			
			int ra = jdbcTemplate.update(sql, id);
			
			return (ra==1);
		}catch(DataAccessException e) {
			LOGGER.warning(e.getMessage());
			//TODO:handle error
			throw e;
		}
	}

	
	public List<CourseMaterial> getAllVersions(int id){
		
		String sql = "select course_material_id, course_material_version, name, description, file,  updated_at, updated_by from course_material_history where course_material_id= ? ;";
		try {
			return  (List<CourseMaterial>)jdbcTemplate.query(sql, new CourseMaterialHistoryRowMapper(), new Object[] { id });
		}catch(EmptyResultDataAccessException e) {
			LOGGER.fine("no course material History for course-material "+ id);
			return null;
		}
	}
}
