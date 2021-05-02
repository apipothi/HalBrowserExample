package com.halbrowser.example.service;

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
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.halbrowser.example.dao.StudentDAO;

@Service
public class StudentService {

	static Map<Integer, StudentDAO> mymap;

	private final static Logger logger = LoggerFactory.getLogger(StudentService.class);

	/* DATA */
	public static Map<Integer, StudentDAO> studentDetails() {

		logger.info("--studentDetails() -- {}");
		mymap=new ConcurrentHashMap<Integer, StudentDAO>();

		mymap.put(1, new StudentDAO(100, "JON", 111));
		mymap.put(2, new StudentDAO(200, "JAX", 222));
		mymap.put(3, new StudentDAO(300, "ANN", 333));
		mymap.put(4, new StudentDAO(400, "OM", 444));

		logger.info("--studentDetails() -- DATA  {}" + mymap);
		return mymap;

	}

	/* GET the Student Details */
	public static Map<Integer, StudentDAO> getStudentDetails() {

		logger.info("--getStudentDetails() -- {}");
		Map<Integer, StudentDAO> studentData = studentDetails();

		logger.info("--getStudentDetails() -- RESPONSE  {}" + studentData);
		return studentData;

	}

	/* GET the Student Details by ID */
	public static List<Entry<Integer, StudentDAO>> getStudentDetailsbyId(int id) {

		logger.info("--getStudentDetailsbyId() -- id {} " + id);

		Map<Integer, StudentDAO> studentData = studentDetails();

		List<Entry<Integer, StudentDAO>> studentbyid = studentData.entrySet().stream().filter(x -> x.getKey() == id)
				.collect(Collectors.toList());

		logger.info("--getStudentDetailsbyId() -- RESPONSE {} " + studentbyid);
		return studentbyid;

	}

	/* PUT the Student Details by ID */
	public static Map<Integer, StudentDAO> createStudentDetails(int id, StudentDAO dao) {

		logger.info("--createStudentDetails() -- {} " + "id" + id + "DAO" + dao);
		Map<Integer, StudentDAO> studentData = studentDetails();

		if (!studentData.containsKey(id)) {

			mymap.put(id, new StudentDAO(dao.getStudentid(), dao.getStudentname(), dao.getStudentphone()));

		} else {

			logger.error("--createStudentDetails() -- Key Duplicate");
		}
		logger.info("--createStudentDetails() -- RESPONSE {} " + mymap);

		return mymap;

	}

	/* POST the Student Details by ID */
	public static Map<Integer, StudentDAO> updateStudentDetails(int id, StudentDAO dao) {

		logger.info("--updateStudentDetails() -- id {} " + id + "DAO" + dao);
		Map<Integer, StudentDAO> studentData = studentDetails();

		if (studentData.containsKey(id)) {

			mymap.put(id, new StudentDAO(dao.getStudentid(), dao.getStudentname(), dao.getStudentphone()));

		} else {

			logger.error("--createStudentDetails() -- Key not abalable ");
		}

		logger.info("--updateStudentDetails() -- RESPONSE {} " + mymap);
		return mymap;
	}

	/* DELETE the Student Details by ID */
	public static Map<Integer, StudentDAO> deleteStudentDetails(int id) {
		logger.info("--deleteStudentDetails() -- id {} " + id);
		Map<Integer, StudentDAO> studentData = studentDetails();

		if (studentData.containsKey(id)) {

			mymap.remove(id);

		} else {
			logger.error("--createStudentDetails() -- Key not abalable ");
		}
		logger.info("--deleteStudentDetails() -- RESPONSE {} " + mymap);
		return mymap;
	}

}
