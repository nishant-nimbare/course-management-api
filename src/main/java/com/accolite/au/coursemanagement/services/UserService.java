package com.accolite.au.coursemanagement.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.au.coursemanagement.models.User;
import com.accolite.au.coursemanagement.repository.UserRepository;

@Service
public class UserService {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	UserRepository userRepo;
	
	public User Login(User u) {
		LOGGER.info(u.toString());
		User res = userRepo.getUser(u.getEmail());
		
		if (res == null) {
			userRepo.createUser(u);
			return userRepo.getUser(u.getEmail());
		}
		return res;
	}
	
	
	public boolean enroll( String userId, int courseId) {
		return userRepo.enroll(userId, courseId);
	}
	
	public List<Integer> getEnrolledCourses(String userId){
		return userRepo.getEnrolledCourses(userId);
	}
}
