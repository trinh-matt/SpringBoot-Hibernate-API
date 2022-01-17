package com.mt.REST.Boot.Demo.models.requests;

public class AddInstructorToCourseRequest {
	private int courseId;
	private int instructorId;
	
	public AddInstructorToCourseRequest() {};
	public AddInstructorToCourseRequest(int courseId, int instructorId) {
		this.courseId = courseId;
		this.instructorId = instructorId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
}
