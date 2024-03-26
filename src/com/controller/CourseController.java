package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.exception.CourseNotFoundException;
import com.model.Course;
import com.model.Student;
import com.model.Teacher;
import com.service.CourseService;


public class CourseController {

	public static void main(String[] args) {
		CourseService courseService  = new CourseService ();  
	     Scanner sc = new Scanner(System.in);
	     while (true)
	     {
	     	System.out.println("************Course Info System ************");
	     	System.out.println("Press 1. to Assign a teacher to the course ");
	     	System.out.println("Press 2. to updates the course information ");	     	
	     	System.out.println("press 3. to Display the Detailed Course Information ");
	     	System.out.println("press 4  to Display the list of students Enrollments for  the course ");
	     	System.out.println("press 5. to Retrives the assigned teacher for the course ");
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
  			System.out.println("Enter course name ");
			String courseName= sc.next();
			System.out.println("Enter credits to the course ");
			int credits = sc.nextInt();  
            System.out.println("Enter Teacher ID");
            int teacherTeacherId=sc.nextInt();
            
			// insert the record in db
			Course c = new Course(courseName , credits ,teacherTeacherId ) ;
	
				try {
					courseService.assignTeacher(c);
					System.out.println("Assigned teacher to course ");

				} catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}

  			
  			break;
  		case 2:
  			System.out.println("Enter Course ID to update");
			int cid = sc.nextInt();
			System.out.println("Enter updated Course Name");
			String ccoursename = sc.next();
			System.out.println("Enter updated credits");
			int ccredits = sc.nextInt();
			System.out.println("Enter updated teacher ID ");
			int cteacherId = sc.nextInt();
				try {
					courseService.updateCourseInfo(cid , ccoursename , ccredits , cteacherId  );
					System.out.println("Record updated");
				} catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
   			
   			break;
  		case 3:
  			try {
				List<Course> list = courseService.displayCourseInfo();
				//Iterate over list
				System.out.println("**********ALL COURSE INFORMATION *********************");
				System.out.println("course_id"+"\t"+"course_name "+"\t"+"credits"+"\t"+"teacher_id");
    			for (Course c1: list) {
    		    	System.out.println(c1.getCourseId()+ "  \t\t "+c1.getCourseName()+"\t\t"+c1.getCredits()+"\t\t"+c1.getTeacherTeacherId());
    		    }
			} catch (SQLException | CourseNotFoundException e1) {
				System.out.println(e1.getMessage());
			}
			  			
			break;
  		case 4:
  			System.out.println("**********list of Students enrolled for a course  *********************");
   			System.out.println("Enter course ID : ");
			int id1 = sc.nextInt();		
			
				try {
					List<Student> list= courseService.getEnrollments(id1);
					System.out.println("student_id" + "       "+"FirstName" + "        "+"LastName"+"     "+"Email"+"               "+"phonenumber" );
					for (Student s: list) {
				    	System.out.println(s.getStudentId()+ "  \t\t"+ s.getFirstName()+ " \t\t  "+ s.getLastName()+" \t"+
					s.getEmail()+ "  \t\t "+s.getPhoneNumber());
					}
				}
					
				catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
  			break;
  		case 5:
  			System.out.println("**********Retrive teachers for a course *********************");
   			System.out.println("Enter course ID : ");
			int id = sc.nextInt();		
			
				try {
					List<Teacher> list= courseService.getTeacher(id);
					System.out.println("teacher_id" + "       "+"FirstName" +"   " +"Last Name "+"       "+"Email" );
					for (Teacher t: list) {
				    	System.out.println(t.getTeacherId()+ " \t \t" + t.getFirstName()+ " \t\t"+t.getLastName()
				    	+" \t\t"+t.getEmail());
				    }
				
					
				} catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
			    
			
  			break;
  			
  			
  		default:
  			System.out.println("Invalid Input given..");
  			break;
  			
	}
	     }sc.close();
	}
	
}
