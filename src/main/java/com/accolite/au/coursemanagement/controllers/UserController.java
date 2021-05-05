package com.accolite.au.coursemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("login")
	public User Login(@RequestParam(value="email", required=true) String email, @RequestParam(value="name", required=true) String name) {
		User u = new User();
		u.setEmail(email);
		u.setName(name);
		return  userService.Login(u);
	}
	
	@PostMapping("enroll/{courseId}")
	public boolean enroll(@PathVariable("courseId") int courseId, @RequestHeader("userId") String userId) {
		return userService.enroll(userId, courseId);
	}

	@GetMapping("enroll")
	public List<Integer> getEnrolledCourses(@RequestHeader("userId") String userId){
		return userService.getEnrolledCourses(userId);
	}
}
