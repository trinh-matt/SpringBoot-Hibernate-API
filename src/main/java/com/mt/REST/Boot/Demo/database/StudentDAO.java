package com.mt.REST.Boot.Demo.database;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mt.REST.Boot.Demo.error.course.CourseNotFoundException;
import com.mt.REST.Boot.Demo.error.student.StudentNotFoundException;
import com.mt.REST.Boot.Demo.models.Course;
import com.mt.REST.Boot.Demo.models.Student;

@Repository
public class StudentDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public StudentDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Student> getAllStudents() {
		Session session = entityManager.unwrap(Session.class);
		Query<Student> getQuery = session.createQuery("FROM Student", Student.class);
		return getQuery.getResultList();
	}
	
	public Student getStudentById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Student student = session.get(Student.class, id);
		if (student == null) {
			throw new StudentNotFoundException(String.format("Student with id %d not found", id));
		}
		return student;
	}
	
	public Student addStudent(Student newStudent) {
		newStudent.setId(0);
		Session session = entityManager.unwrap(Session.class);
		session.save(newStudent);
		return newStudent;
	}
	
	public Student updateStudent(Student updatedStudent) {
		Session session = entityManager.unwrap(Session.class);
		session.update(updatedStudent);
		return updatedStudent;
	}
	
	public Student addCourseToStudent(Student student, int courseId) {
		Session session = entityManager.unwrap(Session.class);
		Course course = session.get(Course.class, courseId);
		Student persistentStudent = session.get(Student.class, student.getId());
		if (course == null) {
			throw new CourseNotFoundException("No course found");
		}
		persistentStudent.addCourse(course);
		session.saveOrUpdate(persistentStudent);
		return persistentStudent;
	}
	
	public void deleteCourseFromStudent(int studentId, int courseId) {
		Session session = entityManager.unwrap(Session.class);
		Student student = session.get(Student.class, studentId);
		
		if (student == null) {
			throw new StudentNotFoundException(String.format("Student with id %d not found", studentId));
		}
		
		List<Course> enrolledCourses = student.getEnrolledCourses();
		
		if (enrolledCourses == null) {
			throw new CourseNotFoundException("No enrolled courses");
		}
		
		Course courseToDelete = null;
		for (Course course : enrolledCourses) {
			if (courseId == course.getId()) {
				courseToDelete = course;
				break;
			}
		}
		
		if (courseToDelete != null) {
			enrolledCourses.remove(courseToDelete);
			session.save(student);
		} else {
			throw new CourseNotFoundException("Student not enrolled");
		}
	}
	
	public void deleteStudent(int studentId) {
		Session session = entityManager.unwrap(Session.class);
		Student studentToDelete = session.get(Student.class, studentId);
		if (studentToDelete == null) {
			throw new StudentNotFoundException(String.format("Student with id %d not found", studentId));
		}
		session.delete(studentToDelete);
	}
	
}
