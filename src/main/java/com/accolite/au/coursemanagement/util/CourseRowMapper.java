package com.accolite.au.coursemanagement.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.Course;

public class CourseRowMapper implements RowMapper<Course> {

	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO handle created by and updated by
		Course c = new Course();
		
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setDescription(rs.getString("description"));
		c.setLocation(rs.getInt("location_id"));
		c.setCreated_at(rs.getTimestamp("created_at"));
		c.setUpdated_at(rs.getTimestamp("updated_at"));
		
		return c;
	}
	

}
