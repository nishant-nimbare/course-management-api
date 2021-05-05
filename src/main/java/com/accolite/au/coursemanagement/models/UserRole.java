package com.accolite.au.coursemanagement.models;

public enum UserRole {
	TRAINER("TRAINER"),
	STUDENT("STUDENT");

	private String role;
	UserRole(String role) {
		this.setRole(role);
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
