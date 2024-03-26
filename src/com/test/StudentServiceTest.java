package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.CourseNotFoundException;

import com.exception.InvalidStudentDataException;
import com.exception.PaymentValidationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.service.StudentService;

public class StudentServiceTest {
	StudentService studentService = new StudentService();

	@Test
	public void displayStudentInfoTest() {

		try {
			List<Student> actualList = studentService.displayStudentInfo();
			Assert.assertNotNull(actualList);
		} catch (SQLException e) {

		} catch (StudentNotFoundException e) {
			Assert.assertEquals("No students to Display".toLowerCase(), e.getMessage().toLowerCase());

		} catch (InvalidStudentDataException e) {
			Assert.assertEquals("Invalid student data provided.".toLowerCase(), e.getMessage().toLowerCase());

		}

	}

	@Test
	public void enrollmentInCourse() {
		// use case 1
		int studentsStudentId = 7;
		int coursesCourseId = 5;

		Enrollment enrl = new Enrollment(LocalDate.now(), studentsStudentId, coursesCourseId);

		try {
			studentService.enrollmentInCourse(enrl);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" Student is not enrolled in the course".toLowerCase(), e.getMessage().toLowerCase());

		}

		studentsStudentId = 10;
		coursesCourseId = 12;

		Enrollment enr2 = new Enrollment(LocalDate.now(), studentsStudentId, coursesCourseId);

		try {
			studentService.enrollmentInCourse(enr2);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" Student is not enrolled in the course".toLowerCase(), e.getMessage().toLowerCase());

		}
	}

	@Test
	public void updateStudentInfoTest() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// use case1
		int eid = 7;
		String efname = "ramaa";
		String elname = "dev";
		String edob1 = "2002-03-19";
		LocalDate edob = LocalDate.parse(edob1, formatter);
		String eemail = "rami@gmail.com";
		String ephoneNumber = "38389393";

		try {
			studentService.updateStudentInfo(eid, efname, elname, edob, eemail, ephoneNumber);
		} catch (SQLException e) {
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found".toLowerCase(), e.getMessage().toLowerCase());

		} catch (InvalidStudentDataException e) {
			Assert.assertEquals("Invalid student data provided".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		eid = 20;
		efname = "ramaa";
		elname = "dev";
		edob1 = "2002-03-19";
		edob = LocalDate.parse(edob1, formatter);
		eemail = "rami@gmail.com";
		ephoneNumber = "38389393";

		try {
			studentService.updateStudentInfo(eid, efname, elname, edob, eemail, ephoneNumber);
		} catch (SQLException e) {
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found".toLowerCase(), e.getMessage().toLowerCase());

		} catch (InvalidStudentDataException e) {
			Assert.assertEquals("Invalid student data provided".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 3
		eid = 7;
		efname = "ramaa";
		elname = "dev";
		edob1 = "2002-03-19";
		edob = LocalDate.parse(edob1, formatter);
		eemail = "rami";
		ephoneNumber = "38389393";

		try {
			studentService.updateStudentInfo(eid, efname, elname, edob, eemail, ephoneNumber);
		} catch (SQLException e) {
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Student not found".toLowerCase(), e.getMessage().toLowerCase());

		} catch (InvalidStudentDataException e) {
			Assert.assertEquals("Invalid student data provided".toLowerCase(), e.getMessage().toLowerCase());

		}

	}

	@Test
	public void makePaymentTest() {
		// use case 1
		double amount = 2000000;
		int studentStudentId = 4;
		Payment pay = new Payment(amount, LocalDate.now(), studentStudentId);

		try {
			studentService.makePayment(pay);
		} catch (SQLException e) {

		} catch (PaymentValidationException e) {
			Assert.assertEquals(" Payment is not done ".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		amount = 2000000;
		studentStudentId = 20;// not valid id
		Payment pay1 = new Payment(amount, LocalDate.now(), studentStudentId);
		try {
			studentService.makePayment(pay1);
		} catch (SQLException e) {

		} catch (PaymentValidationException e) {
			Assert.assertEquals(" Payment is not done ".toLowerCase(), e.getMessage().toLowerCase());

		}

	}

	@Test
	public void getEnrolledCourses() {

		// use case 1
		List<Course> list = new ArrayList<>();
		int id1 = 5;
		Course c = new Course();
		c.setCourseId(6);
		c.setCourseName("SLP");
		c.setCredits(2);
		Course c1 = new Course();
		c1.setCourseId(7);
		c1.setCourseName("SLA");
		c1.setCredits(5);
		list.add(c);
		list.add(c1);
		try {
			Assert.assertEquals(list, studentService.getEnrolledCourses(id1));
		} catch (SQLException e) {

		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		list = new ArrayList<>();
		id1 = 3;
		Course c2 = new Course();
		c2.setCourseId(4);
		c2.setCourseName("MLP");
		c2.setCredits(5);
		Course c3 = new Course();
		c3.setCourseId(2);
		c3.setCourseName("DV");
		c3.setCredits(2);
		Course c4 = new Course();
		c4.setCourseId(2);
		c4.setCourseName("DV");
		c4.setCredits(2);
		list.add(c2);
		list.add(c3);
		list.add(c4);

		try {
			Assert.assertEquals(list, studentService.getEnrolledCourses(id1));
		} catch (SQLException e) {

		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}

		// use case 3
		list = new ArrayList<>();
		id1 = 1;
		Course c5 = new Course();
		c5.setCourseId(5);
		c5.setCourseName("SA");
		c5.setCredits(3);
		list.add(c5);
		try {
			Assert.assertEquals(list, studentService.getEnrolledCourses(id1));
		} catch (SQLException e) {

		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}
	}

	@Test
	public void getPaymentHistory() {
		//use case 1
		List<Payment> list = new ArrayList<>();
		int id=3;
		Payment p = new Payment(2 , 500000, LocalDate.parse("2024-01-04"),3);
		Payment p1 = new Payment(9 , 4000000, LocalDate.parse("2024-03-18"),3);
		Payment p2 = new Payment(10, 2000000, LocalDate.parse("2024-03-20"),3);
		list.add(p);
		list.add(p1);
		list.add(p2);
		try {
			Assert.assertEquals(list, studentService.getPaymentHistory(id));
		} catch (SQLException e) {
			
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}
		// 
		list = new ArrayList<>();
		id=5;
		Payment p3 = new Payment(5 , 5000000, LocalDate.parse("2024-01-12"),5);
		Payment p4 = new Payment(8 , 100000, LocalDate.parse("2021-12-21"),5);
		Payment p5 = new Payment(11, 20000000, LocalDate.parse("2024-03-20"),5);
		Payment p6 = new Payment(12, 3000000, LocalDate.parse("2024-03-20"),5);

		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		try {
			Assert.assertEquals(list, studentService.getPaymentHistory(id));
		} catch (SQLException e) {
			
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}
		list = new ArrayList<>();
		id=6;
		Payment p7 = new Payment(2 , 500000, LocalDate.parse("2024-01-04"),3);
		
		list.add(p7);
		try {
			Assert.assertEquals(list, studentService.getPaymentHistory(id));
		} catch (SQLException e) {
			
		} catch (StudentNotFoundException e) {
			Assert.assertEquals("Invalid Student Id ".toLowerCase(), e.getMessage().toLowerCase());

		}
		
	}

}
