package com.mt.REST.Boot.Demo.database;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mt.REST.Boot.Demo.error.course.CourseNotFoundException;
import com.mt.REST.Boot.Demo.models.Course;
import com.mt.REST.Boot.Demo.models.Instructor;
import com.mt.REST.Boot.Demo.models.requests.AddInstructorToCourseRequest;

@Repository
public class CourseDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public CourseDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Course addCourse(Course newCourse) {
		newCourse.setId(0);
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(newCourse);
		return newCourse;
	}
	
	public List<Course> getAllCourses() {
		Session session = entityManager.unwrap(Session.class);
		Query<Course> getAllQuery = session.createQuery("FROM Course", Course.class);
		return getAllQuery.getResultList();
	}
	
	public Course getCourseById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Course course = session.get(Course.class, id);
		return course;
	}
	
	public List<Course> getCoursesByInstructorId(int instructorId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Course> getQuery = session.createQuery("FROM Course WHERE instructor_id = :instructorId", Course.class);
		getQuery.setParameter("instructorId", instructorId);
		return getQuery.getResultList();
	}
	
	public Course updateCourse(Course updatedCourse) {
		Session session = entityManager.unwrap(Session.class);
		session.update(updatedCourse);
		return updatedCourse;
	}
	
	public Course updateCourseInstructor(AddInstructorToCourseRequest updateRequest) {
		Session session = entityManager.unwrap(Session.class);
		Course course = session.get(Course.class, updateRequest.getCourseId());
		Instructor instructor = session.get(Instructor.class, updateRequest.getInstructorId());
		course.setInstructor(instructor);
		session.update(course);
		return course;
	}
	
	public void deleteCourseById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Course courseToDelete = currentSession.get(Course.class, id);
		if (courseToDelete == null) {
			throw new CourseNotFoundException(String.format("Course with id %d not found", id));
		}
		currentSession.delete(courseToDelete);
	}


}
