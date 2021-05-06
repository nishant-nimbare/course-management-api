package com.accolite.au.coursemanagement.models;

public class CourseLocation {
	int id;
	String name;
	
	
	public CourseLocation() {
		super();
	}

	public CourseLocation(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
