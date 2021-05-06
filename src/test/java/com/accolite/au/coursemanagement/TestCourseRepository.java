package com.accolite.au.coursemanagement;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseLocation;
import com.accolite.au.coursemanagement.repository.CourseRepository;
import com.accolite.au.coursemanagement.util.CourseRowMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestCourseRepository {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	CourseRepository courseRepo;
	
	
	@Test
	void getAllCourses() {
		List<Course> expected = Arrays.asList(new Course(1,"test", "test"));
		when(jdbcTemplate.query( anyString(),  any(CourseRowMapper.class))).thenReturn(expected);
		
		List<Course> actual = courseRepo.getAllCourses();
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).query( anyString(),  any(CourseRowMapper.class));
	}
	
	@Test
	void searchCourses() {
		List<Course> expected = Arrays.asList(new Course(1,"test", "test"));
		expected.get(0).setCourseLocation(new CourseLocation(1, "a"));
		when(jdbcTemplate.query( anyString(),  any(CourseRowMapper.class),  any(Object.class) )).thenReturn(expected);
		
		List<Course> actual = courseRepo.searchCourses("test");
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).query( anyString(),  any(CourseRowMapper.class),  any(Object.class));
	}


	
	@Test
	void getCourse() {
		Course expected = new Course(1,"test", "test");
		when(jdbcTemplate.queryForObject( anyString(),  any(CourseRowMapper.class), eq(1) )).thenReturn(expected);
		
		Course actual = courseRepo.getCourse(1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).queryForObject( anyString(),  any(CourseRowMapper.class), eq(1) );
	}

	@Test
	void createCourse() {
		Course c = new Course(1,"test", "test");
		c.setCourseLocation(new CourseLocation(1, "a"));
		when(jdbcTemplate.update( anyString(),  anyString(),  anyString(),  anyInt() )).thenReturn(1);		
		assertTrue( courseRepo.createCourse(c));
		verify(jdbcTemplate).update( anyString(),  anyString(),  anyString(),  anyInt() );
	}
	

	@Test
	void updateCourse() {
		Course c = new Course(1,"test", "test");
		c.setCourseLocation(new CourseLocation(1, "a"));
		when(jdbcTemplate.update( anyString())).thenReturn(1);		
		assertTrue( courseRepo.updateCourse(1,c));
		verify(jdbcTemplate).update( anyString());
	}
	

	@Test
	void deleteCourse() {
		Course c = new Course(1,"test", "test");
		c.setCourseLocation(new CourseLocation(1, "a"));
		when(jdbcTemplate.update( anyString(),  anyInt())).thenReturn(1);		
		assertTrue( courseRepo.deleteCourse(1));
		verify(jdbcTemplate).update( anyString(),  anyInt());
	}
	
	@Test
	void skillsOfCourse() {
		List<String> expected = Arrays.asList("test1", "test2");
		when(jdbcTemplate.queryForList(anyString(), eq(String.class), eq(1) )).thenReturn(expected);
		
		List<String> actual = courseRepo.skillsOfCourse(1);
		assertEquals(expected,actual);
		
		verify(jdbcTemplate).queryForList(anyString(), eq(String.class), eq(1));
	}
	
	@Test
	void addSkill() {
		when(jdbcTemplate.update(anyString(), anyInt() , anyString() )).thenReturn(1);		
		assertTrue( courseRepo.addSkill(1,"test"));
		verify(jdbcTemplate).update(anyString(), anyInt() , anyString());
	}
	

	@Test
	void removeSkill() {
		when(jdbcTemplate.update(anyString(), anyInt() , anyString() )).thenReturn(1);		
		assertTrue( courseRepo.removeSkill(1,"test"));
		verify(jdbcTemplate).update(anyString(), anyInt() , anyString());
	}

	@Test
	void prerequisitesOfCourse() {
		List<String> expected = Arrays.asList("test1", "test2");
		when(jdbcTemplate.queryForList(anyString(), eq(String.class), eq(1) )).thenReturn(expected);
		
		List<String> actual = courseRepo.prerequisitesOfCourse(1);
		assertEquals(expected,actual);
		
		verify(jdbcTemplate).queryForList(anyString(), eq(String.class), eq(1));
	}
	
	@Test
	void addPrerequisite() {
		when(jdbcTemplate.update(anyString(), anyInt() , anyString() )).thenReturn(1);		
		assertTrue( courseRepo.addPrerequisite(1,"test"));
		verify(jdbcTemplate).update(anyString(), anyInt() , anyString());
	}
	

	@Test
	void removeprerequisite() {
		when(jdbcTemplate.update(anyString(), anyInt() , anyString() )).thenReturn(1);		
		assertTrue( courseRepo.removeprerequisite(1,"test"));
		verify(jdbcTemplate).update(anyString(), anyInt() , anyString());
	}

}
