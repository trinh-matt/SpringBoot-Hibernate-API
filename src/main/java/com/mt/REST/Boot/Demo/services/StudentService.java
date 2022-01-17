package com.mt.REST.Boot.Demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.REST.Boot.Demo.database.StudentDAO;
import com.mt.REST.Boot.Demo.models.Student;

@Service
public class StudentService {
	
	private StudentDAO studentDAO;
	
	@Autowired
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Transactional
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
	
	@Transactional
	public Student getStudentById(int id) {
		return studentDAO.getStudentById(id);
	}
	
	@Transactional
	public Student addStudent(Student newStudent) {
		return studentDAO.addStudent(newStudent);
	}
	
	@Transactional
	public Student updateStudent(Student updatedStudent) {
		return studentDAO.updateStudent(updatedStudent);
	}
	
	@Transactional
	public Student addCourseToStudent(Student student, int courseId) {
		return studentDAO.addCourseToStudent(student, courseId);
	}
	
	
	@Transactional
	public boolean deleteCourseFromStudent(int studentId, int courseId) {
		studentDAO.deleteCourseFromStudent(studentId, courseId);
		return true;
	}
	
	@Transactional
	public boolean deleteStudent(int studentId) {
		studentDAO.deleteStudent(studentId);
		return true;
	}
}
