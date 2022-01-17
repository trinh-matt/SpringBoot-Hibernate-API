package com.mt.REST.Boot.Demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.REST.Boot.Demo.database.CourseDAO;
import com.mt.REST.Boot.Demo.models.Course;
import com.mt.REST.Boot.Demo.models.Student;
import com.mt.REST.Boot.Demo.models.requests.AddInstructorToCourseRequest;

@Service
public class CourseService {
	
	private CourseDAO courseDAO;
	
	@Autowired
	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	@Transactional
	public Course addCourse(Course newCourse) {
		return courseDAO.addCourse(newCourse);
	}
	
	@Transactional
	public List<Course> getAllCourses() {
		return courseDAO.getAllCourses();
	}
	
	@Transactional
	public Course getCourseById(int id) {
		return courseDAO.getCourseById(id);
	}
	
	@Transactional
	public List<Course> getCoursesByInstructorId(int instructorId) {
		return courseDAO.getCoursesByInstructorId(instructorId);
	}
	
	@Transactional
	public List<Student> getEnrolledStudents(int courseId) {
		return courseDAO.getCourseById(courseId).getEnrolledStudents();
	}
	
	@Transactional
	public Course updateCourse(Course updatedCourse) {
		return courseDAO.updateCourse(updatedCourse);
	}
	
	@Transactional
	public Course updateCourseInstructor(AddInstructorToCourseRequest updateRequest) {
		return courseDAO.updateCourseInstructor(updateRequest);
	}
	
	@Transactional
	public boolean deleteCourseById(int courseId) {
		courseDAO.deleteCourseById(courseId);
		return true;
	}

}
