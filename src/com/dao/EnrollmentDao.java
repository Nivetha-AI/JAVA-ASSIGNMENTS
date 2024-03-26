package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.InvalidEnrollmentDataException;
import com.model.Course;
import com.model.Student;

public interface EnrollmentDao {
	public List<Student> getStudent(int id) throws SQLException,InvalidEnrollmentDataException;
	public List<Course> getCourses(int id1) throws SQLException,InvalidEnrollmentDataException;



}
