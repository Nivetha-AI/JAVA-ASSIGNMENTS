package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.CourseNotFoundException;
import com.model.Course;
import com.model.Student;
import com.model.Teacher;
import com.service.CourseService;

public class CourseServiceTest {
	CourseService courseService = new CourseService();

	@Test
	public void assignTeacherTest() {
		// use case 1
		String courseName = "Python";
		int credits = 3;
		int teacherTeacherId = 3;

		Course c = new Course(courseName, credits, teacherTeacherId);
		try {
			courseService.assignTeacher(c);
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
			courseService.assignTeacher(c1);
		} catch (SQLException e) {
		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" Teacher is not Assigned to the course".toLowerCase(), e.getMessage().toLowerCase());

		}
	}

	@Test
	public void updateCourseInfoTest() {
		// use case 1
		int cid = 12;
		String ccoursename = "OOPS";
		int ccredits = 10;
		int cteacherId = 2;

		try {
			courseService.updateCourseInfo(cid, ccoursename, ccredits, cteacherId);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		cid = 19;
		ccoursename = "OOPS";
		ccredits = 10;
		cteacherId = 2;

		try {
			courseService.updateCourseInfo(cid, ccoursename, ccredits, cteacherId);
		} catch (SQLException e) {

		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course not found".toLowerCase(), e.getMessage().toLowerCase());

		}
	}
	@Test
	public void displayCourseInfoTest()
	{
	// use case 1
		try {
			List <Course> actualList = courseService.displayCourseInfo();
			Assert.assertNotNull(actualList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("No Courses to Display".toLowerCase(), e.getMessage().toLowerCase());

		}
		
	}
	@Test
	public void getTeacherTest() {
		// use case 1
		List<Teacher> list = new ArrayList<>();
        int id =2;
	 	Teacher t= new Teacher(2 , "Surenther" , "Iyanar" , "suren@gmail.com");
        list.add(t);
        try {
			Assert.assertEquals(list, courseService.getTeacher(id));
		} catch (SQLException e) {
			
		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" No Teacher is Assigned to course".toLowerCase(), e.getMessage().toLowerCase());

		}
        // use case 2
        list = new ArrayList<>();
        id =20;
	 	Teacher t1= new Teacher(2 , "Surenther" , "Iyanar" , "suren@gmail.com");
        list.add(t1);
        try {
			Assert.assertEquals(list, courseService.getTeacher(id));
		} catch (SQLException e) {
			
		} catch (CourseNotFoundException e) {
			Assert.assertEquals(" No Teacher is Assigned to course".toLowerCase(), e.getMessage().toLowerCase());

		}

  
		
	}
	@Test
	public void getEnrollmentsTest() {
		// use case 1
		List<Student> list = new ArrayList<>();
        int id1 =6;
        Student s = new Student(5,"jancy","sankar",LocalDate.parse("2004-12-05"),"jancy@gmail.com","5458967");
        list.add(s);
        try {
			Assert.assertEquals(list, courseService.getEnrollments(id1));
		} catch (SQLException e) {
			
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course ID is not found".toLowerCase(), e.getMessage().toLowerCase());

		}
        list = new ArrayList<>();
        id1 =4;
        Student s1 = new Student(3,"ram","kumar",LocalDate.parse("2000-04-09"),"ram@gmail.com","98754567");
        list.add(s1);
        try {
			Assert.assertEquals(list, courseService.getEnrollments(id1));
		} catch (SQLException e) {
			
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course ID is not found".toLowerCase(), e.getMessage().toLowerCase());

		}
  
        list = new ArrayList<>();
        id1 =19;
        Student s2 = new Student(3,"ram","kumar",LocalDate.parse("2000-04-09"),"ram@gmail.com","98754567");
        list.add(s2);
        try {
			Assert.assertEquals(list, courseService.getEnrollments(id1));
		} catch (SQLException e) {
			
		} catch (CourseNotFoundException e) {
			Assert.assertEquals("Course ID is not found".toLowerCase(), e.getMessage().toLowerCase());

		}
  
		
	}

	
	
}
