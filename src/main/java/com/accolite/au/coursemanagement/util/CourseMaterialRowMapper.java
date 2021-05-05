package com.accolite.au.coursemanagement.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.CourseMaterial;

public class CourseMaterialRowMapper implements RowMapper<CourseMaterial>{

	@Override
	public CourseMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {

		CourseMaterial cm = new CourseMaterial();

		cm.setId(rs.getInt("id"));
		cm.setVersion(rs.getInt("version"));
		cm.setName(rs.getString("name"));
		cm.setDescription(rs.getString("description"));
		cm.setCourse_id(rs.getInt("course_id"));
		
		cm.setCreated_at(rs.getTimestamp("created_at"));
		cm.setUpdated_at(rs.getTimestamp("updated_at"));
		
		try {
			cm.setFile(rs.getBytes("file"));
		}catch(SQLException e) {
			
		}
		
		return cm;
	}

}
