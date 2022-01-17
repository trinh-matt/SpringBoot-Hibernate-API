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

import com.mt.REST.Boot.Demo.models.Student;
import com.mt.REST.Boot.Demo.services.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student newStudent) {
		return studentService.addStudent(newStudent);
	}
	
	@PutMapping("/student/{id}")
	public Student updateStudent(@RequestBody Student updatedStudent) {
		return studentService.updateStudent(updatedStudent);
	}
	
	@PutMapping("/student/course/{id}")
	public Student addCourseToStudent(@RequestBody Student student, @PathVariable("id") int courseId) {
		return studentService.addCourseToStudent(student, courseId);
	}
	
	// Remove course from student 
	@DeleteMapping("/student/course/{id}")
	public boolean deleteCourseFromStudent(@RequestBody Student student, @PathVariable("id") int courseId) {
		return studentService.deleteCourseFromStudent(student.getId(), courseId);
	}
	
	@DeleteMapping("/student/{id}")
	public boolean deleteStudent(@PathVariable("id") int studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	
}
