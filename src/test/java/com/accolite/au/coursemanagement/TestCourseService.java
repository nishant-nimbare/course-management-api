package com.accolite.au.coursemanagement;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.repository.CourseRepository;
import com.accolite.au.coursemanagement.repository.UserRepository;
import com.accolite.au.coursemanagement.services.CourseService;
import com.accolite.au.coursemanagement.util.UserCourseResponse;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestCourseService {

	@Mock
	private CourseRepository courseRepository;
	
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private CourseService courseService;
	
	
	@Test
	void getAllCourses() {
		List<Course> expected = Arrays.asList(new Course(1,"test", "test"));
		given(courseRepository.getAllCourses()).willReturn(expected);
		
		List<Course> actual = courseService.getAllCourses();
		assertEquals(expected, actual);
		
		verify(courseRepository).getAllCourses();
	}
	
	@Test
	void getSearchCourses() {
		List<Course> expected = Arrays.asList(new Course(1,"test", "test"));
		given(courseRepository.searchCourses("test")).willReturn(expected);
		
		List<Course> actual = courseService.getAllCourses("test");
		assertEquals(expected, actual);
		
		verify(courseRepository).searchCourses("test");
	}
	
	@Test
	void getStudentCourses() {
		List<Course> allCourses = Arrays.asList(new Course(1,"test", "test"), new Course(2,"test2", "test2"));
	
		given(courseRepository.getAllCourses()).willReturn(allCourses);
		given(userRepo.getEnrolledCourses( "test@email.com" )).willReturn(Arrays.asList(2));

		List<UserCourseResponse> actual = courseService.getStudentCourses("test@email.com", null);

		assertFalse(actual.get(0).isEnrolled());
		assertTrue(actual.get(1).isEnrolled());

		verify(courseRepository).getAllCourses();
		verify(userRepo).getEnrolledCourses( "test@email.com" );
	}
	
	@Test
	void getCourse() {
		Course expected = new Course(1,"test", "test");
		given(courseRepository.getCourse(1)).willReturn(expected);
		Course actual = courseService.getCourse(1);
		assertEquals(expected, actual);
		verify(courseRepository).getCourse(1);
		
		given(courseRepository.getCourse(-1)).willThrow(new EmptyResultDataAccessException(0));
		assertNull(courseService.getCourse(-1));
	}
	
	@Test
	void getCourseStudent() {
		Course c = new Course(1,"test", "test");
		
		given(courseRepository.getCourse(1)).willReturn(c);
		given(userRepo.isEnrolled("test@email.com", 1)).willReturn(true);
		
		UserCourseResponse actual = courseService.getCourseStudent(1, "test@email.com");
		assertTrue(actual.isEnrolled());
		
		verify(courseRepository).getCourse(1);
		verify(userRepo).isEnrolled("test@email.com", 1);
		
		given(courseRepository.getCourse(-1)).willThrow(new EmptyResultDataAccessException(0));
		assertNull(courseService.getCourseStudent(-1,""));

	}
	
	@Test
	void createCourse() {
		Course c = new Course(1,"test", "test");
		given(courseRepository.createCourse(c)).willReturn(true);
		assertTrue(courseService.createCourse(c));
		verify(courseRepository).createCourse(c);
	}
	
	@Test
	void updateCourse() {
		Course c = new Course(1,"test", "test");
		given(courseRepository.updateCourse(1,c)).willReturn(true);
		assertTrue(courseService.updateCourse(1, c));
		verify(courseRepository).updateCourse(1,c);
	}
	
	@Test
	void deleteCourse() {
		given(courseRepository.deleteCourse(1)).willReturn(true);
		assertTrue(courseService.deleteCourse(1));
		verify(courseRepository).deleteCourse(1);
	}
	
	@Test
	void skillsOfCourse() {
		List<String> expected = Arrays.asList("test1", "test2");
		given(courseRepository.skillsOfCourse(1)).willReturn(expected);
		
		List<String> actual = courseService.skillsOfCourse(1);
		assertEquals(expected, actual);
		
		verify(courseRepository).skillsOfCourse(1);
	}
	
	@Test
	void addSkill() {
		given(courseRepository.addSkill(1, "a")).willReturn(true);
		assertTrue(courseService.addSkill(1, "a"));
		verify(courseRepository).addSkill(1, "a");
	}
	
	@Test
	void removeSkill() {
		given(courseRepository.removeSkill(1, "a")).willReturn(true);
		assertTrue(courseService.removeSkill(1, "a"));
		verify(courseRepository).removeSkill(1, "a");
	}
	
	@Test
	void prerequisitesOfCourse() {
		List<String> expected = Arrays.asList("test1", "test2");
		given(courseRepository.prerequisitesOfCourse(1)).willReturn(expected);
		
		List<String> actual = courseService.prerequisitesOfCourse(1);
		assertEquals(expected, actual);
		
		verify(courseRepository).prerequisitesOfCourse(1);
	}
	
	@Test
	void addPrerequisite() {
		given(courseRepository.addPrerequisite(1, "a")).willReturn(true);
		assertTrue(courseService.addPrerequisite(1, "a"));
		verify(courseRepository).addPrerequisite(1, "a");
	}
	
	@Test
	void removeprerequisite() {
		given(courseRepository.removeprerequisite(1, "a")).willReturn(true);
		assertTrue(courseService.removeprerequisite(1, "a"));
		verify(courseRepository).removeprerequisite(1, "a");
	}
	
	
}
