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
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.accolite.au.coursemanagement.models.CourseMaterial;
import com.accolite.au.coursemanagement.repository.CourseMaterialRepository;
import com.accolite.au.coursemanagement.util.CourseMaterialHistoryRowMapper;
import com.accolite.au.coursemanagement.util.CourseMaterialRowMapper;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestCourseMaterialRepository {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	CourseMaterialRepository materialRepo;
	
	@Test
	void getCourseMaterial() {
		CourseMaterial expected = new CourseMaterial(1,1, 1, "test", "test", null);
		when(jdbcTemplate.queryForObject(anyString(), any(CourseMaterialRowMapper.class), eq(1) )).thenReturn(expected);
		
		CourseMaterial actual = materialRepo.getCourseMaterial(1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).queryForObject( anyString(),  any(CourseMaterialRowMapper.class), eq(1) );
	}

	@Test
	void getCourseMaterialForCourse() {
		List<CourseMaterial> expected = Arrays.asList(new CourseMaterial(1,1, 1, "test", "test", null));
		when(jdbcTemplate.query( anyString(),  any(CourseMaterialRowMapper.class), eq(1) )).thenReturn(expected);
		
		List<CourseMaterial> actual = materialRepo.getCourseMaterialForCourse(1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).query( anyString(),  any(CourseMaterialRowMapper.class), eq(1) );
	}
	
	
	@Test
	void createCourseMaterial() {
		CourseMaterial cm = new CourseMaterial(1,1, 1, "test", "test", null);
		when(jdbcTemplate.update(anyString(), anyInt(), anyString(), anyString(), eq(null) )).thenReturn(1);
		
		assertTrue( materialRepo.createCourseMaterial(cm));
		
		verify(jdbcTemplate).update(anyString(), anyInt(), anyString(), anyString(), eq(null) );
	}
	
	@Test
	void updateCourseMaterial() {
		CourseMaterial cm = new CourseMaterial(1,1, 1, "test", "test", null);
		when(jdbcTemplate.update(anyString(), any(Object[].class), any(int[].class) )).thenReturn(1);
		
		assertTrue( materialRepo.updateCourseMaterial(1,cm));
		
		verify(jdbcTemplate).update(anyString(), any(Object[].class),  any(int[].class) );
	}
	
	@Test
	void deleteCourseMaterial() {
		when(jdbcTemplate.update(anyString(), eq(1))).thenReturn(1);
		
		assertTrue( materialRepo.deleteCourseMaterial(1));
		
		verify(jdbcTemplate).update(anyString(), eq(1));
	}
	
	@Test
	void getHistory() {
		List<CourseMaterial> expected = Arrays.asList(new CourseMaterial(1,1, 1, "test", "test", null));
		when(jdbcTemplate.query(anyString(), any(CourseMaterialHistoryRowMapper.class), eq(1) )).thenReturn(expected);
		
		List<CourseMaterial> actual = materialRepo.getHistory(1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).query(anyString(), any(CourseMaterialHistoryRowMapper.class), eq(1) );
	}
	
	@Test
	void downloadLatest() {
		byte[] expected = "test".getBytes();
		when(jdbcTemplate.queryForObject(anyString(), Matchers.<RowMapper<byte[]>> any(), eq(1) )).thenReturn(expected);
		
		byte[] actual = materialRepo.downloadLatest(1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).queryForObject( anyString(), Matchers.<RowMapper<byte[]>> any(),  eq(1) );
	
	}
	
	@Test
	void downloadMaterial() {
		
		byte[] expected = "test".getBytes();
		when(jdbcTemplate.queryForObject(anyString(), Matchers.<RowMapper<byte[]>> any(), eq(1), eq(1) )).thenReturn(expected);
		
		byte[] actual = materialRepo.downloadMaterial(1,1);
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).queryForObject( anyString(), Matchers.<RowMapper<byte[]>> any(),  eq(1), eq(1) );
	
	}
}
