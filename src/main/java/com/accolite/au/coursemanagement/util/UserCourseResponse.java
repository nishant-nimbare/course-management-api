package com.accolite.au.coursemanagement.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.accolite.au.coursemanagement.models.Course;
import com.accolite.au.coursemanagement.models.CourseLocation;

public class UserCourseResponse {

	//TODO: add created by and updated by
	private int id;
	private String name, description;
	private Timestamp created_at,updated_at;
	private CourseLocation courseLocation;
	private boolean enrolled, created;
	

	
	public static UserCourseResponse fromCourse(Course c){
		UserCourseResponse that = new UserCourseResponse();
		
		that.id = c.getId();
		that.name = c.getName();
		that.description = c.getDescription();
		that.created_at = c.getCreated_at();
		that.updated_at = c.getUpdated_at();
		that.courseLocation = c.getCourseLocation();
		
		return that;
	}
	
	public static UserCourseResponse fromCourse(Course c, boolean enrolled, boolean created){
		UserCourseResponse that = fromCourse(c);

		that.enrolled = enrolled;
		that.created = created; 
		return that;
	}
	
	public static List<UserCourseResponse> fromCourse(List<Course> cl){
		List<UserCourseResponse> res = new ArrayList<>(cl.size());
		for(Course c: cl) {
			res.add(fromCourse(c));
		}
		return res;
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
	public CourseLocation getCourseLocation() {
		return courseLocation;
	}
	public void setCourseLocation(CourseLocation courseLocation) {
		this.courseLocation = courseLocation;
	}
	public boolean isEnrolled() {
		return enrolled;
	}
	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "UserCourseResponse [id=" + id + ", name=" + name + ", enrolled=" + enrolled + ", created=" + created
				+ "]";
	}

	

}
