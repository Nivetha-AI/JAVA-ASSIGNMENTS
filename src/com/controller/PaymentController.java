package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;
import com.service.PaymentService;

public class PaymentController {
	public static void main(String[] args) {
		PaymentService paymentService  = new PaymentService ();  
	    Scanner sc = new Scanner(System.in);
	    while (true)
	    {
	    	System.out.println("************Payment Info System ************");
	    	System.out.println("Press 1. to Retrives the student associated with the payment ");
	    	System.out.println("Press 2. to Retrives the payment Amount ");
	    	System.out.println("Press 3. to Retrives the payment date");
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
				System.out.println("**********List of Students Associated with payments  ********************");
	   			System.out.println("Enter Payment ID : ");
				int id = sc.nextInt();		
				
					try {
						List<Student> list= paymentService.getStudent(id);
						System.out.println("student_id" + "       "+"first_name" + "        "+ "last_name"+"   "+"     "+"DOB"
						+" \t\t "+"email"+ "\t \t"+"phoneNumber");
						for (Student s: list) {
					    	System.out.println(s.getStudentId()+"\t \t "+ s.getFirstName() + "\t\t  "+ s.getLastName()+ "\t\t  "+
		    		    			s.getDateOfBirth()+ "\t\t "+s.getEmail() +" \t\t " + s.getPhoneNumber());
					    }
					
						
					} catch (SQLException |  PaymentValidationException e) {
						System.out.println(e.getMessage());
					}
				break;
			case 2:
				System.out.println("**********Retrives the payment Amount  ********************");
	   			System.out.println("Enter Payment ID : ");
				int id1 = sc.nextInt();		
				
					try {
						List<Payment> list= paymentService.getPaymentAmount(id1);
						
						for (Payment p: list) {
					    	System.out.println("PAYMENT ID: " +p.getPaymentId()+" " +"PAYMENT AMOUNT :" +p.getAmount());
					    			}
					
						
					} catch (SQLException |  PaymentValidationException e) {
						System.out.println(e.getMessage());
					}
				break;
			case 3:
				System.out.println("**********Retrives the payment date ********************");
	   			System.out.println("Enter Payment ID : ");
				int id2 = sc.nextInt();		
				
					try {
						List<Payment> list= paymentService.getPaymentDate(id2);
						
						for (Payment p: list) {
					    	System.out.println("PAYMENT ID: " +p.getPaymentId()+" " +"PAYMENT DATE :" +p.getPaymentDate());
					    			}
					
						
					} catch (SQLException | PaymentValidationException e) {
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
