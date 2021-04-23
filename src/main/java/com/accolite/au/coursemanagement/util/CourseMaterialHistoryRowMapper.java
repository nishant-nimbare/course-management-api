package com.accolite.au.coursemanagement.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.CourseMaterial;

public class CourseMaterialHistoryRowMapper implements RowMapper<CourseMaterial> {

	@Override
	public CourseMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseMaterial cm = new CourseMaterial();

		cm.setId(rs.getInt("course_material_id"));
		cm.setVersion(rs.getInt("course_material_version"));
		cm.setName(rs.getString("name"));
		cm.setDescription(rs.getString("description"));
		cm.setFile(rs.getBytes("file"));
		cm.setUpdated_at(rs.getTimestamp("updated_at"));
	
		return cm;
	}

}
