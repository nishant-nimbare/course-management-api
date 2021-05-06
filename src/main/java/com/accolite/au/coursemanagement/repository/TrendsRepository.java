package com.accolite.au.coursemanagement.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class TrendsRepository {
private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	JdbcTemplate jdbcTemplate;


	public Map<String,Integer> enrollmentTrend() {
		String sql = "SELECT e.course_id, c.name as course_name, count(*) as count FROM enrollment as e JOIN course as c ON e.course_id=c.id  GROUP BY course_id;";
		try {
			return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String,Integer>>(){
			    @Override
			    public Map<String,Integer> extractData(ResultSet rs) throws SQLException,DataAccessException {
			        HashMap<String,Integer> mapRet= new HashMap<String,Integer>();
			        while(rs.next()){
			            mapRet.put(rs.getString("course_name"),rs.getInt("count"));
			        }
			        return mapRet;
			    }
			});
		}catch(DataAccessException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
	
	
	public Map<String,Integer> topFiveSkills() {
		String sql = "SELECT skill, count(*) as count FROM course_skills GROUP BY skill ORDER BY count DESC limit 5;";
		try {
			return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String,Integer>>(){
			    @Override
			    public Map<String,Integer> extractData(ResultSet rs) throws SQLException,DataAccessException {
			        HashMap<String,Integer> mapRet= new HashMap<String,Integer>();
			        while(rs.next()){
			            mapRet.put(rs.getString("skill"),rs.getInt("count"));
			        }
			        return mapRet;
			    }
			});
		}catch(DataAccessException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}


	public Map<String,Integer> topFivePrereq() {
		String sql = "SELECT prerequisite, count(*) as count FROM course_prerequisites GROUP BY prerequisite ORDER BY count DESC limit 5;";
		try {
			return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String,Integer>>(){
			    @Override
			    public Map<String,Integer> extractData(ResultSet rs) throws SQLException,DataAccessException {
			        HashMap<String,Integer> mapRet= new HashMap<String,Integer>();
			        while(rs.next()){
			            mapRet.put(rs.getString("prerequisite"),rs.getInt("count"));
			        }
			        return mapRet;
			    }
			});
		}catch(DataAccessException e) {
			LOGGER.severe(e.getMessage());
			return null;
		}
	}
	
	
}
