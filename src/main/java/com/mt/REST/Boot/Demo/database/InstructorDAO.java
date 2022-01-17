package com.mt.REST.Boot.Demo.database;

import java.util.List;

import com.mt.REST.Boot.Demo.models.Instructor;

public interface InstructorDAO {
	public List<Instructor> getInstructors();
	public Instructor getInstructorById(int id);
	public Instructor addInstructor(Instructor newInstructor);
	public Instructor updateInstructor(Instructor updatedInstructor);
	public void deleteInstructor(int id);
}
