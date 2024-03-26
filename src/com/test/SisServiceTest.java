package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dto.CourseStatisticsDto;
import com.exception.CourseNotFoundException;
import com.exception.PaymentValidationException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.service.SisService;

public class SisServiceTest {
	SisService sisService = new SisService();
	@Test
	public void enrollmentInCourseTest() {
		// use case 1
		int studentsStudentId = 7;
		int coursesCourseId = 5;

		Enrollment enrl = new Enrollment(LocalDate.now(), studentsStudentId, coursesCourseId);

		try {
			sisService.enrollmentInCourse(enrl);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" Student is not enrolled in the course".toLowerCase(), e.getMessage().toLowerCase());

		}
		
		studentsStudentId = 20;
		coursesCourseId = 18;

		Enrollment enr2 = new Enrollment(LocalDate.now(), studentsStudentId, coursesCourseId);

		try {
			sisService.enrollmentInCourse(enr2);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" Student is not enrolled in the course".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	@Test
	public void assignTeacherToCourseTest() {
	// use case 1
			String courseName = "Python";
			int credits = 3;
			int teacherTeacherId = 3;

			Course c = new Course(courseName, credits, teacherTeacherId);
			try {
				sisService.assignTeacherToCourse(c);
			} catch (SQLException e) {
			} catch (CourseNotFoundException e) {
				Assert.assertEquals(" Teacher is not Assigned to the course".toLowerCase(), e.getMessage().toLowerCase());

			}
			// use case 2
			courseName = "Python";
			credits = 3;
			teacherTeacherId = 10;

			Course c1 = new Course(courseName, credits, teacherTeacherId);
			try {
				sisService.assignTeacherToCourse(c1);
			} catch (SQLException e) {
			} catch (CourseNotFoundException e) {
				Assert.assertEquals(" Teacher is not Assigned to the course".toLowerCase(), e.getMessage().toLowerCase());

			}
	}
	
	@Test
	public void recordPaymentTest() {
		// use case 1
				double amount = 2000000;
				int studentStudentId = 4;
				Payment pay = new Payment(amount, LocalDate.now(), studentStudentId);

				try {
					sisService.recordPayment(pay);
				} catch (SQLException e) {

				} catch (PaymentValidationException e) {
					Assert.assertEquals(" Payment is not done ".toLowerCase(), e.getMessage().toLowerCase());

				}
				// use case 2
				amount = 2000000;
				studentStudentId = 20;// not valid id
				Payment pay1 = new Payment(amount, LocalDate.now(), studentStudentId);
				try {
					sisService.recordPayment(pay1);
				} catch (SQLException e) {

				} catch (PaymentValidationException e) {
					Assert.assertEquals(" Payment is not done ".toLowerCase(), e.getMessage().toLowerCase());

				}

	}

	@Test
	public void generatePaymentReportTest() {
		// use case 1
		List<Student> list = new ArrayList<>();
		int id = 3;
		Student s = new Student(2, "Nithi", "Dev", LocalDate.parse("2002-09-16"), "nithu@gmail.com ", "9867543");
		list.add(s);

		try {
			Assert.assertEquals(list, sisService.generatePaymentReport(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}
		// use case 2
		list = new ArrayList<>();
		id = 10;
		Student s1 = new Student(3, "ram", "kumar", LocalDate.parse("2000-04-09"), "ram@gmail.com", "98754567");
		list.add(s1);

		try {
			Assert.assertEquals(list, sisService.generatePaymentReport(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}

		// use case 3
		list = new ArrayList<>();
		id = 24;
		Student s2 = new Student(4, "visha", "sam", LocalDate.parse("2001-02-15"), "visha@gmail.com", "78889567");
		list.add(s2);

		try {
			Assert.assertEquals(list, sisService.generatePaymentReport(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}

	}

	@Test
	public void generateEnrollmentReportTest() {
		// use case 1
		List<Student> list = new ArrayList<>();
		int id1 = 6;
		Student s = new Student(5, "jancy", "sankar", LocalDate.parse("2004-12-05"), "jancy@gmail.com", "5458967");
		list.add(s);
		try {
			Assert.assertEquals(list, sisService.generateEnrollmentReport(id1));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		list = new ArrayList<>();
		id1 = 4;
		Student s1 = new Student(3, "ram", "kumar", LocalDate.parse("2000-04-09"), "ram@gmail.com", "98754567");
		list.add(s1);
		try {
			Assert.assertEquals(list, sisService.generateEnrollmentReport(id1));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}

		list = new ArrayList<>();
		id1 = 19;
		Student s2 = new Student(3, "ram", "kumar", LocalDate.parse("2000-04-09"), "ram@gmail.com", "98754567");
		list.add(s2);
		try {
			Assert.assertEquals(list, sisService.generateEnrollmentReport(id1));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}

	}

	@Test
	public void calculateCourseStatisticsTest() {
		// use case 1
		List<CourseStatisticsDto> list = new ArrayList<>();// container

		int couid = 2;
		CourseStatisticsDto courstat = new CourseStatisticsDto(2, 6, 13000000);
		list.add(courstat);
		try {
			Assert.assertEquals(list, sisService.calculateCourseStatistics(couid));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		list = new ArrayList<>();// container

		couid = 3;
		CourseStatisticsDto courstat1 = new CourseStatisticsDto(3, 4, 500000);
		list.add(courstat1);
		try {
			Assert.assertEquals(list, sisService.calculateCourseStatistics(couid));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 3
		list = new ArrayList<>();// container
		couid = 23;
		CourseStatisticsDto courstat2 = new CourseStatisticsDto(0, 0, 0);
		list.add(courstat2);
		try {
			Assert.assertEquals(list, sisService.calculateCourseStatistics(couid));
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Invalid Course ID".toLowerCase(), e.getMessage().toLowerCase());

		}

	}

}
