package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.exception.CourseNotFoundException;
import com.exception.InvalidStudentDataException;
import com.exception.PaymentValidationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.service.StudentService;


public class StudentController {

	public static void main(String[] args) throws NullPointerException {
		StudentService studentService  = new StudentService ();  
	     Scanner sc = new Scanner(System.in);
	     while (true)
	     {
	     	System.out.println("************Student Info System ************");
	     	System.out.println("Press 1. to enroll a student in course ");
	     	System.out.println("Press 2. to updates the student information ");
	     	System.out.println("Press 3. to insert payment made by student");
	     	System.out.println("press 4. to Display the student Information ");
	     	System.out.println("press 5  to Display the list of courses in which the student is enrolled ");
	     	System.out.println("press 6. to Display the list of Payment records for student");
	     	System.out.println("Press 0. to Exit");
	     	System.out.println("**********************************************");
	     	
	     	int input = sc.nextInt();
	     	// what if user types 0 and wants to exit ??
	     	if(input ==0) {
	     		System.out.println("Exiting ... Thank You");
	     		break;
	     	}
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	     	switch(input){
   		case 1:
   			
			System.out.println("Enter Student ID ");
			int studentsStudentId = sc.nextInt();
			System.out.println("Enter course ID ");
		    int coursesCourseId = sc.nextInt();
			
			// insert the record in db
			Enrollment enrl = new Enrollment( LocalDate.now(),studentsStudentId ,coursesCourseId) ;
	
				try {
					studentService.enrollmentInCourse(enrl);
					System.out.println("Enrolled a Student in course ");

				} catch (SQLException |CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				
			break ;
   			
   			
   			
   		case 2:
			System.out.println("Enter student ID to update");
			int eid = sc.nextInt();
			System.out.println("Enter updated first name");	
			String efname = sc.next();
			System.out.println("Enter updated last name");
			String elname = sc.next();
			System.out.println("Enter updated dob : yyyy-mm-dd");
			String edob1 = sc.next();
			LocalDate edob = LocalDate.parse(edob1, formatter);
			System.out.println("Enter updated email ");
			String eemail = sc.next();
			System.out.println("Enter updated Phone number ");
			String ephonenumber = sc.next();
				
		
				try {
					studentService.updateStudentInfo(eid , efname ,elname , edob , 
							eemail,ephonenumber  );
					System.out.println("Record updated");
				} catch (SQLException | StudentNotFoundException | InvalidStudentDataException e) {
					System.out.println(e.getMessage());
				}
   			
   			break;
   		case 3:

			System.out.println("Enter Amount ");
			double amount = sc.nextDouble();
			System.out.println("Enter student Id to pay ");
			int studentStudentId = sc.nextInt();  

			
			// insert the record in db
			Payment pay = new Payment(amount, LocalDate.now(),studentStudentId ) ;
	
				try {
					studentService.makePayment(pay);
					System.out.println("Payment is made by student ");
				} catch (SQLException | PaymentValidationException e) {
					System.out.println(e.getMessage());
				}
			

   			break;
   		case 4:
   			try {
				List<Student> list = studentService.displayStudentInfo();
				//Iterate over list
				System.out.println("**********ALL STUDENTS *********************");
    			for (Student s: list) {
    		    	System.out.println(s.getStudentId()+"  "+ s.getFirstName() + "  "+ s.getLastName()+ "  "+
    		    			s.getDateOfBirth()+ " "+s.getEmail() +"  " + s.getPhoneNumber());
    		    }
			} catch (SQLException | StudentNotFoundException | InvalidStudentDataException e1) {
				System.out.println(e1.getMessage());
			}
			  			
			break;
   			
   		case 5:
			System.out.println("**********List of courses in which student is enrolled *********************");
   			System.out.println("Enter student ID : ");
			int id1 = sc.nextInt();
				try {
					List<Course>  list = studentService.getEnrolledCourses(id1);
					System.out.println("course_id" + "       "+"course_name" + "        "+"credits" );
					for (Course c: list) {
				    	System.out.println(c.getCourseId()+"\t  " +c.getCourseName()+ " \t "+c.getCredits());
				    }
				} catch (SQLException | StudentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			//Iterate over list
			
   			
   			break;
   		case 6:
   			System.out.println("**********List of payment recors for the students *********************");
   			System.out.println("Enter student ID : ");
			int id = sc.nextInt();		
			
				try {
					List<Payment> list= studentService.getPaymentHistory(id);
					System.out.println("Payment_id" + "       "+"Amount" + "        "+"Payment_date"+"     "+"student_id" );
					for (Payment p: list) {
				    	System.out.println(p.getPaymentId()+ "           \t\t \t \t           \t\t \t \t" + p.getAmount()+ "             \t\t\t\t"+p.getPaymentDate()
				    	+" \t"+p.getStudentsStudentId());
				    }
				
					
				} catch (SQLException| StudentNotFoundException e) {
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
