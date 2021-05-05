package com.accolite.au.coursemanagement.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.CourseLocation;

public class CourseLocationRowMapper implements RowMapper<CourseLocation>{

	@Override
	public CourseLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseLocation l = new CourseLocation();
		l.setId(rs.getInt("id"));
		l.setName(rs.getString("name"));
		return l;
	}
	

}
