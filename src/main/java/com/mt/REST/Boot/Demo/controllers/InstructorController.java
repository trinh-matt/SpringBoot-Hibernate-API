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

import com.mt.REST.Boot.Demo.error.instructor.InstructorNotFoundException;
import com.mt.REST.Boot.Demo.models.Instructor;
import com.mt.REST.Boot.Demo.services.InstructorService;

@RestController
@RequestMapping("/api")
public class InstructorController {
	
	private InstructorService instructorService;
	
	@Autowired
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
	
	@GetMapping("/instructors")
	public List<Instructor> getAllInstructors() {
		return instructorService.getAll();
	}
	
	@GetMapping("/instructor/{id}")
	public Instructor getInstructorById(@PathVariable("id") int id) {
		Instructor instructor = instructorService.getInstructorById(id);
		
		if (instructor == null) {
			throw new InstructorNotFoundException(String.format("Instructor with id %d not found", id));
		}
		
		return instructor;
	}
	
	@PostMapping("/instructors")
	public Instructor addInstructor(@RequestBody Instructor newInstructor) {
		return instructorService.addInstructor(newInstructor);
	}
	
	@PutMapping("/instructor/{id}")
	public Instructor updateInstructor(@RequestBody Instructor updatedInstructor) {
		return instructorService.updateInstructor(updatedInstructor);
	}
	
	@DeleteMapping("/instructor/{id}")
	public boolean deleteInstructor(@PathVariable("id") int id) {
		return instructorService.deleteInstructor(id);
	}
}
