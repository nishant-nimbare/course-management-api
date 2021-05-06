package com.accolite.au.coursemanagement.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.au.coursemanagement.repository.TrendsRepository;

@RestController
@RequestMapping("/trends")
public class TrendsController {

	@Autowired
	TrendsRepository trendsRepo;
	
	@GetMapping("/enrollment")
	public Map<String,Integer> enrollmentTrend() {
		return trendsRepo.enrollmentTrend();
	}
	

	@GetMapping("/skill")
	public Map<String,Integer> topFiveSkills() {
		return trendsRepo.topFiveSkills();
	}
	

	@GetMapping("/prereq")
	public Map<String,Integer> topFivePrereq() {
		return trendsRepo.topFivePrereq();
	}
}
