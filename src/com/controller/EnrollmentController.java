package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.InvalidEnrollmentDataException;
import com.model.Course;
import com.model.Student;
import com.service.EnrollmentService;

public class EnrollmentController {

	public static void main(String[] args) {
		EnrollmentService enrollmentService  = new EnrollmentService ();  
	     Scanner sc = new Scanner(System.in);
	     while (true)
	     {
	     	System.out.println("************Enrollment Info System ************");
	     	System.out.println("Press 1. to Retrive the student associated with the enrollment ");
	     	System.out.println("Press 2. to : Retrieves the course associated with the enrollment. ");
	     	System.out.println("Press 0. to Exit");
	     	System.out.println("**********************************************");
	     	
	     	int input = sc.nextInt();
	     	// what if user types 0 and wants to exit ??
	     	if(input ==0) {
	     		System.out.println("Exiting ... Thank You");
	     		break;
	     	}
	      	switch(input){
	   		case 1:
	   			System.out.println("**********List of  student associated with enrollments*********************");
	   			System.out.println("Enter Enrollment ID : ");
				int id = sc.nextInt();		
				
					try {
						List<Student> list= enrollmentService.getStudent(id);
						System.out.println("student_id" + "       "+"FirstName" + "        "+"LastName"+"     "+"Email"+"  "+"phonenumber" );
						for (Student s: list) {
					    	System.out.println(s.getStudentId()+ "  "+ s.getFirstName()+ "   "+ s.getLastName()+"   "+
						s.getEmail()+ "   "+s.getPhoneNumber());
					    }
					
						
					} catch (SQLException | InvalidEnrollmentDataException e) {
						System.out.println(e.getMessage());
					}
	   			
				break;
	   		case 2:
	   			System.out.println("**********List of  courses associated with the enrollment *********************");
	   			System.out.println("Enter Enrollment ID : ");
				int id1 = sc.nextInt();		
				
					try {
						List<Course> list= enrollmentService.getCourses(id1);
						System.out.println("course_id" + "       "+"CourseName" + "        "+"credits" );
						for (Course c: list) {
					    	System.out.println(c.getCourseId()+"\t  "+c.getCourseName()+ "   \t"+ c.getCredits());
					    }
						
					} catch (SQLException | InvalidEnrollmentDataException e) {
						System.out.println(e.getMessage());
					}
	   			
	   			
	   			
	   			
	   			
	   			break;
	   		default:
	   			System.out.println("Invalid Input given..");
				break;
	   			

	}

}
	     sc.close();
}
}