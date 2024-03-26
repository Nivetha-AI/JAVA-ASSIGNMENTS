package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InvalidEnrollmentDataException;
import com.model.Course;
import com.model.Student;
import com.service.EnrollmentService;

public class EnrollmentServiceTest {
	EnrollmentService  enrollmentService= new EnrollmentService();
	

	@Test
	public void getStudentTest() {
		//use case 1
		List<Student> list = new ArrayList<>();
		int id = 4;
		Student s = new Student(3,"ram","kumar", LocalDate.parse("2000-04-09"),"ram@gmail.com","98754567");
		list.add(s);
		
		try {
			Assert.assertEquals(list, enrollmentService.getStudent(id));
		} catch (SQLException e) {
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());
		}
       // use case 2
		list = new ArrayList<>();
		id = 8;
		Student s1 = new Student(4,"visha","sam", LocalDate.parse("2001-02-15"),"visha@gmail.com","78889567");
		list.add(s1);
		
		try {
			Assert.assertEquals(list, enrollmentService.getStudent(id));
		} catch (SQLException e) {
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());
		}
		
		 // use case 3
		list = new ArrayList<>();
		id = 2;
		Student s2 = new Student(4,"visha","sam", LocalDate.parse("2001-02-15"),"visha@gmail.com","78889567");
		list.add(s2);
		
		try {
			Assert.assertEquals(list, enrollmentService.getStudent(id));
		} catch (SQLException e) {
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());
		}
				
	}
	
	public void getCoursesTest() {
		// use case 1
		List<Course> list = new ArrayList<>();
		int id1=5;
		Course c= new Course();
		c.setCourseId(5);
	 	c.setCourseName("SA");
	 	c.setCredits(3);
		list.add(c);
		try {
			Assert.assertEquals(list, enrollmentService.getCourses(id1));
		} catch (SQLException e) {
			
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		list = new ArrayList<>();
		id1=2;
		Course c1= new Course();
		c1.setCourseId(7);
	 	c1.setCourseName("SLA");
	 	c1.setCredits(5);
		list.add(c1);
		try {
			Assert.assertEquals(list, enrollmentService.getCourses(id1));
		} catch (SQLException e) {
			
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());

		}			
		// use case 3
		list = new ArrayList<>();
		id1=2;
		Course c2= new Course();
		c2.setCourseId(5);
	 	c2.setCourseName("SA");
	 	c2.setCredits(3);
		list.add(c2);
		try {
			Assert.assertEquals(list, enrollmentService.getCourses(id1));
		} catch (SQLException e) {
			
		} catch (InvalidEnrollmentDataException e) {
			Assert.assertEquals("Invalid Enrollment ID".toLowerCase(), e.getMessage().toLowerCase());

		}		
	}
}
