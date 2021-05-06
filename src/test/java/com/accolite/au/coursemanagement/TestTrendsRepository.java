package com.accolite.au.coursemanagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.repository.CourseRepository;
import com.accolite.au.coursemanagement.repository.TrendsRepository;
import com.accolite.au.coursemanagement.util.CourseRowMapper;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestTrendsRepository {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	TrendsRepository trendsRepo;

	static Map<String,Integer> expected = new HashMap<String,Integer>();
	
	@BeforeClass
	public static void initializeMap() {
		expected.put("a", 10);
		expected.put("b", 20);	
	}
	
	@Test
	void enrollmentTrend() {
		when(jdbcTemplate.query( anyString(),  any(ResultSetExtractor.class))).thenReturn(expected);
		assertEquals(expected, trendsRepo.enrollmentTrend());
		verify(jdbcTemplate).query( anyString(),  any(ResultSetExtractor.class));
	}

	@Test
	void topFiveSkills() {
		when(jdbcTemplate.query( anyString(),  any(ResultSetExtractor.class))).thenReturn(expected);
		assertEquals(expected, trendsRepo.topFiveSkills());
		verify(jdbcTemplate).query( anyString(),  any(ResultSetExtractor.class));
	}
	
	@Test
	void topFivePrereq() {
		when(jdbcTemplate.query( anyString(),  any(ResultSetExtractor.class))).thenReturn(expected);
		assertEquals(expected, trendsRepo.topFivePrereq());
		verify(jdbcTemplate).query( anyString(),  any(ResultSetExtractor.class));
	}
}
