package com.accolite.au.coursemanagement.models;

import java.sql.Timestamp;

public class CourseMaterial {

	//TODO: add created by and updated by
	private int id,course_id, version;
	private String name, description;
	
	private Timestamp created_at,updated_at;
	private byte[] file;
	
	
	public CourseMaterial() {
		super();
	}
	
	public CourseMaterial(int id, int course_id, int version, String name, String description, byte[] file) {
		super();
		this.id = id;
		this.course_id = course_id;
		this.version = version;
		this.name = name;
		this.description = description;
		this.file = file;
	}




	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
	
	public byte[] getFile() {
		return file;
	}
	
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "CourseMaterial [id=" + id + ", course_id=" + course_id + ", name=" + name + ", description="
				+ description + "]";
	} 
	
	
	
}
