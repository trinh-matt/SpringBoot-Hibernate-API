package com.mt.REST.Boot.Demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.REST.Boot.Demo.database.InstructorDAO;
import com.mt.REST.Boot.Demo.models.Instructor;

@Service
public class InstructorService {
	private InstructorDAO instructorDAO;
	
	@Autowired
	public InstructorService(InstructorDAO instructorDAO) {
		this.instructorDAO = instructorDAO;
	}
	
	@Transactional
	public List<Instructor> getAll() {
		return instructorDAO.getInstructors();
	}
	
	@Transactional
	public Instructor getInstructorById(int id) {
		return instructorDAO.getInstructorById(id);
	}
	
	@Transactional
	public Instructor addInstructor(Instructor newInstructor) {
		return instructorDAO.addInstructor(newInstructor);
	}
	
	@Transactional
	public Instructor updateInstructor(Instructor updatedInstructor) {
		return instructorDAO.updateInstructor(updatedInstructor);
	}
	
	@Transactional
	public boolean deleteInstructor(int id) {
		instructorDAO.deleteInstructor(id);
		return true;
	}
}
