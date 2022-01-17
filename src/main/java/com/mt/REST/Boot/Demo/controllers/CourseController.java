package com.mt.REST.Boot.Demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mt.REST.Boot.Demo.models.Course;
import com.mt.REST.Boot.Demo.models.Student;
import com.mt.REST.Boot.Demo.models.requests.AddInstructorToCourseRequest;
import com.mt.REST.Boot.Demo.services.CourseService;

@RestController
@RequestMapping("/api")
public class CourseController {
	
	private CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping("/course")
	public Course addCourse(@RequestBody Course newCourse) {
		return courseService.addCourse(newCourse);
	}
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/courses/instructor/{id}")
	public List<Course> getCoursesByInstructor(@PathVariable("id") int instructorId) {
		return courseService.getCoursesByInstructorId(instructorId);
	}
	
	@GetMapping("/course/{id}")
	public Course getCourseById(@PathVariable("id") int id) {
		return courseService.getCourseById(id);
	}
	
	@GetMapping("/course/{id}/enrollment")
	public List<Student> getEnrolledStudents(@PathVariable("id") int courseId) {
		return courseService.getEnrolledStudents(courseId);
	}
	
	@PutMapping("/course")
	public Course updateCourse(@RequestBody Course updatedCourse) {
		return courseService.updateCourse(updatedCourse);
	}
	
	@PutMapping("/course/instructor")
	public Course updateCourseInstructor(@RequestBody AddInstructorToCourseRequest updateRequest) {
		return courseService.updateCourseInstructor(updateRequest);
	}
	
	@DeleteMapping("/course/{id}")
	public boolean deleteCourseById(@PathVariable("id") int courseId) {
		return courseService.deleteCourseById(courseId);
	}
}
