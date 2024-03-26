package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.StudentDaoImpl;
import com.exception.CourseNotFoundException;
import com.exception.InvalidStudentDataException;
import com.exception.PaymentValidationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;

public class StudentService {
	StudentDaoImpl studentDao = new StudentDaoImpl();

	public List<Student> displayStudentInfo() throws SQLException,StudentNotFoundException,InvalidStudentDataException {
			List <Student> list = studentDao.displayStudentInfo();
			return list;
	}

	public  void enrollmentInCourse(Enrollment enrl)  throws SQLException ,CourseNotFoundException{
		
		studentDao.enrollmentInCourse(enrl);
	}

	public void updateStudentInfo(int eid, String efname, String elname, LocalDate edob, String eemail,
			String ephonenumber) throws SQLException, StudentNotFoundException ,InvalidStudentDataException{
		studentDao.updateStudentInfo(eid , efname ,elname , edob ,eemail,
				ephonenumber  );
		
	}

	public void makePayment(Payment pay) throws SQLException,PaymentValidationException{
		
		studentDao.makePayment(pay);
	}

	public List<Course> getEnrolledCourses(int id1) throws SQLException ,StudentNotFoundException {
		List <Course> list = studentDao.getEnrolledCourses(id1);
		return list;
		
	}

	

	public  List<Payment>  getPaymentHistory(int id) throws SQLException,StudentNotFoundException{
		List<Payment> list= studentDao.getPaymentHistory(id);
		return list;
	}

}
