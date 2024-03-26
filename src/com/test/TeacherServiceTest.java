package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;
import com.service.TeacherService;

public class TeacherServiceTest {
	TeacherService teacherService = new TeacherService();

	@Test
	public void updateTeacherTest() {
		// use case 1
		int tid = 4;
		String tfirstName = "Sathya";
		String tlastName = "Krishnasamy";
		String temail = "sath@gmail.com";
		try {
			teacherService.updateTeacher(tid, tfirstName, tlastName, temail);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("Teacher not found".toLowerCase(), e.getMessage().toLowerCase());
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Name and email are required.".toLowerCase(), e.getMessage().toLowerCase());
		}
		// use case 2-teacher not found Exception
		tid = 8;
		tfirstName = "Sathya";
		tlastName = "Krishnasamy";
		temail = "sath@gmail.com";
		try {
			teacherService.updateTeacher(tid, tfirstName, tlastName, temail);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("Teacher not found".toLowerCase(), e.getMessage().toLowerCase());
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Name and email are required.".toLowerCase(), e.getMessage().toLowerCase());
		}
		// use case 3-InvalidTeacherDataException
		tid = 4;
		tfirstName = "";
		tlastName = "Krishnasamy";
		temail = "sath@gmail.com";
		try {
			teacherService.updateTeacher(tid, tfirstName, tlastName, temail);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("Teacher not found".toLowerCase(), e.getMessage().toLowerCase());
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Name and email are required.".toLowerCase(), e.getMessage().toLowerCase());
		}
		// use case 3-InvalidTeacherDataException
		tid = 4;
		tfirstName = "sathya";
		tlastName = "Krishnasamy";
		temail = "";
		try {
			teacherService.updateTeacher(tid, tfirstName, tlastName, temail);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("Teacher not found".toLowerCase(), e.getMessage().toLowerCase());
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Name and email are required.".toLowerCase(), e.getMessage().toLowerCase());
		}
	}
	@Test
	public void displayTeacherInfoTest() {
		
		try {
			List<Teacher> actualList = teacherService.displayTeacherInfo();
			Assert.assertNotNull(actualList);
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No Teacher Info to show".toLowerCase(), e.getMessage().toLowerCase());
		} catch (InvalidTeacherDataException e) {
			Assert.assertEquals("Name and email are required.".toLowerCase(), e.getMessage().toLowerCase());
		}
		 
	}
	@Test
	public void getAssignedCourseTest() {
		//use case 1
		List<Course> list = new ArrayList<>();
		int id = 4;
		Course course = new Course(4,"MLP",5,4);
		list.add(course);
		try {
			Assert.assertEquals(list, teacherService.getAssignedCourse(id));
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No Teacher is assigned to course".toLowerCase(), e.getMessage().toLowerCase());
		}
       
		// use case 2
		
		list = new ArrayList<>();
		id = 2;
		Course c1= new Course(2,"DV",2,2);
		Course c2 = new Course(5,"SA",3,2);
		Course c3 = new Course(7,"SLA",5,2);
		Course c4 = new Course(8,"JP",5,2);
		Course c5 = new Course(9,"PLD",5,2);
	    list.add(c1);
	    list.add(c2);
	    list.add(c3);
	    list.add(c4);
	    list.add(c5);
	    try {
			Assert.assertEquals(list, teacherService.getAssignedCourse(id));
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No Teacher is assigned to course".toLowerCase(), e.getMessage().toLowerCase());
		}
	    // use case 3
	    list = new ArrayList<>();
		id = 10;
			    try {
			Assert.assertEquals(list, teacherService.getAssignedCourse(id));
		} catch (SQLException e) {
		} catch (TeacherNotFoundException e) {
			Assert.assertEquals("No Teacher is assigned to course".toLowerCase(), e.getMessage().toLowerCase());
		}
	    
		
	}

}
