package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.CourseStatisticsDto;
import com.exception.CourseNotFoundException;
import com.exception.PaymentValidationException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;

public interface SisDao {
	public  void enrollmentInCourse(Enrollment enrl)  throws SQLException , CourseNotFoundException;
	public void assignTeacherToCourse(Course c)throws SQLException ,CourseNotFoundException;
	public void recordPayment(Payment pay) throws SQLException, PaymentValidationException;
	public List<Student> generateEnrollmentReport(int id1) throws SQLException,CourseNotFoundException ;

	public List<Student> generatePaymentReport(int id) throws SQLException, PaymentValidationException;
	public List<CourseStatisticsDto> calculateCourseStatistics(int couid) throws SQLException,CourseNotFoundException;

}
