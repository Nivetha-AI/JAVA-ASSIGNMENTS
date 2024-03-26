package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidStudentDataException;
import com.exception.PaymentValidationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;

public interface StudentDao {
	public List<Student>displayStudentInfo() throws SQLException,StudentNotFoundException,InvalidStudentDataException ;
	public  void enrollmentInCourse(Enrollment enrl)  throws SQLException,CourseNotFoundException;
	public void updateStudentInfo(int eid, String efname, String elname, LocalDate edob, String eemail,
			String ephonenumber) throws SQLException, StudentNotFoundException ,InvalidStudentDataException;
	public void makePayment(Payment pay)throws SQLException,PaymentValidationException;
	public List<Course> getEnrolledCourses(int id1) throws SQLException ,StudentNotFoundException;
	public  List<Payment>  getPaymentHistory(int id) throws SQLException,StudentNotFoundException ;

}
