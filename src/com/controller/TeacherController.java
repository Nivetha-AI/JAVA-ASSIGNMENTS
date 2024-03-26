package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;

import com.model.Teacher;
import com.service.TeacherService;


public class TeacherController {

	public static void main(String[] args) {
		TeacherService teacherService  = new TeacherService ();  
	     Scanner sc = new Scanner(System.in);
	     while (true)
	     {
	     	System.out.println("************Teeacher Info System ************");
	     	System.out.println("Press 1. to update the teacher Information ");
	     	System.out.println("Press 2. to Display the detailed information about the teacher ");
	     	System.out.println("Press 3. to Retrive the list of courses assigned to teacher ");
	     	System.out.println("Press 0. to Exit");
	     	System.out.println("**********************************************");
	     	
	     	int input = sc.nextInt();
	    
	     	if(input ==0) {
	     		System.out.println("Exiting ... Thank You");
	     		break;
	     	}
	     	switch(input) {
	     		case 1:
	     			System.out.println("Enter Teacher ID to update");
	    			int tid = sc.nextInt();
	     			System.out.println("Enter first name ");
	    			String tfirstName = sc.next();
	    			System.out.println("Enter last name ");
	    			String tlastName = sc.next();
	    			System.out.println("Enter email");
	    			String temail= sc .next();
	      				
	    			
	    			try {
						teacherService.updateTeacher(tid, tfirstName, tlastName, temail);
						System.out.println("Record updated");

					} catch (SQLException | TeacherNotFoundException |  InvalidTeacherDataException e) {
						System.out.println(e.getMessage());
					}
	    				
						
	    			break ;	
	    			
	     			
	     			
	     		case 2:
	     			try {
	    				List<Teacher> list = teacherService.displayTeacherInfo();
	    				//Iterate over list
	    				System.out.println("**********ALL TEACHER INFORMATION *********************");
	    				System.out.println("Techer_id"+"     "+"first_name"+ "    "+"last_name"+ "     "+ "email");
	        			for (Teacher t: list) {
	        		    	System.out.println(t.getTeacherId()+   "          "+t.getFirstName()+"            "+t.getLastName()+ "            "
	        		    			+t.getEmail());
	        		    }
	    			} catch (SQLException | TeacherNotFoundException |  InvalidTeacherDataException e1) {
	    				System.out.println(e1.getMessage());
	    			}
	    			  	
	     			break;
	     		case 3:
	     			System.out.println("**********List of courses assigned to teacher ********************");
	       			System.out.println("Enter teacher ID : ");
	    			int id = sc.nextInt();		
	    			
	    				try {
	    					List<Course> list= teacherService.getAssignedCourse(id);
	    					System.out.println("Course_id" + "       "+"course_name" + "        "+"credits"+"     "+"teacher_id" );
	    					for (Course c: list) {
	    				    	System.out.println(c.getCourseId()+ "\t \t" + c.getCourseName()+ " \t \t\t"+c.getCredits()
	    				    	+" \t\t"+c.getTeacherTeacherId());
	    				    }
	    				
	    					
	    				} catch (SQLException | TeacherNotFoundException e) {
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