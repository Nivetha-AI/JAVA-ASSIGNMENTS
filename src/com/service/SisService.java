package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.SisDaoImpl;
import com.dto.CourseStatisticsDto;
import com.exception.CourseNotFoundException;
import com.exception.PaymentValidationException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;

public class SisService {
	SisDaoImpl sisDao = new SisDaoImpl();

	public void enrollmentInCourse(Enrollment enrl) throws SQLException, CourseNotFoundException {

		sisDao.enrollmentInCourse(enrl);
	}

	public void assignTeacherToCourse(Course c) throws SQLException ,CourseNotFoundException{
		sisDao.assignTeacherToCourse(c);

	}

	public void recordPayment(Payment pay) throws SQLException, PaymentValidationException {

		sisDao.recordPayment(pay);
	}

	public List<Student> generatePaymentReport(int id) throws SQLException, PaymentValidationException {
		List<Student> list = sisDao.generatePaymentReport(id);
		return list;
	}

	public List<Student> generateEnrollmentReport(int id1) throws SQLException ,CourseNotFoundException{
		List<Student> list = sisDao.generateEnrollmentReport(id1);
		return list;
	}

	public List<CourseStatisticsDto> calculateCourseStatistics(int couid) throws SQLException,CourseNotFoundException{
		 List<CourseStatisticsDto> list =sisDao.calculateCourseStatistics(couid);
		return list;
	}

}
