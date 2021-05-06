package com.accolite.au.coursemanagement;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.models.UserRole;
import com.accolite.au.coursemanagement.repository.UserRepository;
import com.accolite.au.coursemanagement.util.UserRowMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestUserRepository {

	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	UserRepository userRepo;
	
	@Test
	void getUser() {
		User expected = new User("test@email.com", "test", null, UserRole.STUDENT);
		when(jdbcTemplate.queryForObject(anyString(), any(UserRowMapper.class), eq("test@email.com"))).thenReturn(expected);
		
		User actual = userRepo.getUser("test@email.com");
		assertEquals(expected, actual);
		
		verify(jdbcTemplate).queryForObject(anyString(), any(UserRowMapper.class), eq("test@email.com"));
	}

	@Test
	void createUser() {
		User u = new User("test@email.com", "test", null, UserRole.STUDENT);
		
		when(jdbcTemplate.update(anyString(), eq(u.getEmail()), eq(u.getName()) )).thenReturn(1);
		assertTrue(userRepo.createUser(u));
		verify(jdbcTemplate).update(anyString(), eq(u.getEmail()), eq(u.getName()) );
	}
	
	@Test
	void enroll() {
		when(jdbcTemplate.update(anyString(), eq("1"), eq(1) )).thenReturn(1);
		assertTrue(userRepo.enroll("1",1));
		verify(jdbcTemplate).update(anyString(),eq("1"), eq(1) );
	}
	
	@Test
	void getEnrolledCourses() {
		List<Integer> expected = Arrays.asList(1, 2);
		when(jdbcTemplate.queryForList(anyString(), eq(Integer.class), eq("1") )).thenReturn(expected);
		
		List<Integer> actual = userRepo.getEnrolledCourses("1");
		assertEquals(expected,actual);
		
		verify(jdbcTemplate).queryForList(anyString(), eq(Integer.class), eq("1"));
	}
	
//	@Test
//	void isEnrolled() {
//		when(jdbcTemplate.update(anyString(), eq(Integer.class), any(Object[].class) )).thenReturn(1);
////		assertTrue(
//				userRepo.isEnrolled("1",1);
////				);
//		verify(jdbcTemplate).update(anyString(), eq(Integer.class), any(Object[].class) );
//	}
}
