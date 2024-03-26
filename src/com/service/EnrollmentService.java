package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.EnrollmentDaoImpl;
import com.exception.InvalidEnrollmentDataException;
import com.model.Course;
import com.model.Student;

public class EnrollmentService {

	EnrollmentDaoImpl enrollmentDao = new EnrollmentDaoImpl();
	public List<Student> getStudent(int id) throws SQLException,InvalidEnrollmentDataException {
		List <Student> list = enrollmentDao.getStudent(id);
		return list;
	}
	public List<Course> getCourses(int id1)throws SQLException,InvalidEnrollmentDataException {
		List <Course> list = enrollmentDao.getCourses(id1);
		return list;
		
	}

}
