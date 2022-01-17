package com.mt.REST.Boot.Demo.database;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mt.REST.Boot.Demo.error.instructor.InstructorNotFoundException;
import com.mt.REST.Boot.Demo.models.Instructor;

@Repository
public class InstructorDAOImpl implements InstructorDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public InstructorDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Instructor> getInstructors() {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Instructor> getAllQuery = currentSession.createQuery("from Instructor", Instructor.class);
		
		List<Instructor> allInstructors = getAllQuery.getResultList();
		
		return allInstructors;
	}
	
	@Override
	public Instructor getInstructorById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Instructor.class, id);
	}
	
	@Override
	public Instructor addInstructor(Instructor newInstructor) {
		newInstructor.setId(0);
		
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(newInstructor);
		return newInstructor;
	}
	
	@Override
	public Instructor updateInstructor(Instructor updatedInstructor) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(updatedInstructor);
		return updatedInstructor;
	}
	
	@Override
	public void deleteInstructor(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Instructor instructorToDelete = currentSession.get(Instructor.class, id);
		if (instructorToDelete == null) {
			throw new InstructorNotFoundException(String.format("Instructor with id %d not found", id));
		}
		Query deleteQuery = currentSession.createQuery("DELETE from Instructor "
														+ "WHERE id = :id");
		deleteQuery.setParameter("id", id);
		deleteQuery.executeUpdate();
	}

}
