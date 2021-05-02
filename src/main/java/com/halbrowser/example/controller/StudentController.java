package com.halbrowser.example.controller;

/*
 * Author    : API POTHI
 * YouTube   : https://www.youtube.com/apipothi
 * Play List : MICROSERVICE-SPRINGBOOT
 * JAVA      : 11
 * Program   : 12. How to do the REST “API Documentation ” using "HAL Browser & Explorer" in Spring Boot Project
*/
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.halbrowser.example.dao.StudentDAO;
import com.halbrowser.example.service.StudentService;
import com.halbrowser.example.to.Student;

@RestController
public class StudentController {

	private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService service;

	@GetMapping("/studentsdetails")
	public Map<Integer, StudentDAO> getMyStudentsDetails() {
		logger.info("--getMyStudentsDetails() -- {}");
		return service.getStudentDetails();
	}

	@GetMapping("/studentsdetailsbyid/{id}")
	public List<Entry<Integer, StudentDAO>> getMyStudentsDetailsbyID(@PathVariable int id) {
		logger.info("--getMyStudentsDetailsbyID() -- {}");
		return service.getStudentDetailsbyId(id);
	}

	@PostMapping("/updatestudentsdetailsbyid/{id}")
	public Map<Integer, StudentDAO> updateMyStudentsDetailsbyID(@PathVariable int id, @RequestBody Student to) {

		StudentDAO dao = new StudentDAO();
		dao.setStudentid(to.getStudentid());
		dao.setStudentname(to.getStudentname());
		dao.setStudentphone(to.getStudentphone());

		Map<Integer, StudentDAO> updatestudentdetals = service.updateStudentDetails(id, dao);
		logger.info("--updateMyStudentsDetailsbyID() -- {}" + updatestudentdetals);
		return updatestudentdetals;

	}

	@PutMapping("/createstudentsdetailsbyid/{id}")
	public Map<Integer, StudentDAO> createMyStudentsDetailsbyID(@PathVariable int id, @RequestBody Student to) {

		StudentDAO dao = new StudentDAO();
		dao.setStudentid(to.getStudentid());
		dao.setStudentname(to.getStudentname());
		dao.setStudentphone(to.getStudentphone());

		Map<Integer, StudentDAO> newstudentdata = service.createStudentDetails(id, dao);
		logger.info("--createMyStudentsDetailsbyID() -- {}" + newstudentdata);
		return newstudentdata;

	}

	@DeleteMapping("/deletestudentsdetailsbyid/{id}")
	public Map<Integer, StudentDAO> deleteMyStudentsDetailsbyID(@PathVariable int id) {

		Map<Integer, StudentDAO> afterdeleting = service.deleteStudentDetails(id);
		logger.info("--deleteMyStudentsDetailsbyID() -- {}" + afterdeleting);
		return afterdeleting;

	}
}
