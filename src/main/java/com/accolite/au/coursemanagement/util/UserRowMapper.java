package com.accolite.au.coursemanagement.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.models.UserRole;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		User u = new User();
		u.setEmail(rs.getString("email"));
		u.setName(rs.getString("name"));
		u.setRole(UserRole.valueOf(rs.getString("role")));
		u.setCreated_at(rs.getTimestamp("created_at"));
		return u;
	}

}
