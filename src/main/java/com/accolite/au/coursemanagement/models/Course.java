package com.accolite.au.coursemanagement.models;

import java.sql.Timestamp;

public class Course {

	//TODO: add created by and updated by
	private int id;
	private String name, description;
	private Timestamp created_at,updated_at;
	private CourseLocation courseLocation;


	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Course(int id, String name, String description, Timestamp created_at, Timestamp updated_at,
			CourseLocation courseLocation) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.courseLocation = courseLocation;
	}

	public Course() {
		// TODO Auto-generated constructor stub
	}

	public CourseLocation getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(CourseLocation courseLocation) {
		this.courseLocation = courseLocation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", location=" + courseLocation.getName() + ", name=" + name + ", description=" + description + "]";
	}
}
