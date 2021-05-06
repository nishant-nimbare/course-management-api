package com.accolite.au.coursemanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.models.UserRole;
import com.accolite.au.coursemanagement.repository.UserRepository;
import com.accolite.au.coursemanagement.services.UserService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TestUserService {

	
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	void Login() {
		User expected = new User("test", "test",null, UserRole.STUDENT);
		given(userRepo.getUser("test")).willReturn(expected);
		
		
		User actual = userService.Login(expected);
		assertEquals(expected, actual);
		
		verify(userRepo).getUser("test");
	}

	@Test
	void enroll() {
		given(userRepo.enroll("a",1)).willReturn(true);
		assertTrue(userService.enroll("a",1));
		verify(userRepo).enroll("a",1);
	}
}
