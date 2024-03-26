package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class PaymentDaoImpl implements PaymentDao {

	public List<Student> getStudent(int id) throws SQLException, PaymentValidationException {
		Connection conn = DBUtil.getDBconn() ;
	      List<Student> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select * from students s join payments p on s.student_id =p.students_student_id where payment_id=?";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	// Attach the value to ?
			pstmt.setInt(1,id);
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	 
			 //step 3: read the resultset and Iterate over it to save the object
			    while(rst.next()) {
			       int studentId=rst.getInt("student_id");
			       String firstName = rst.getString("First_name");
			 	   String lastName = rst.getString("Last_name");
			       String dateOfBirth = rst.getString("Date_of_Birth");
			       String email = rst.getString("email");
			       String PhoneNumber = rst.getString("phone_number");
			 	  
			 	   
			 	   Student s = new Student();
			 	   s.setStudentId(studentId);
			 	   s.setFirstName(firstName);
			 	   s.setLastName(lastName);
			 	   s.setDateOfBirth(LocalDate.parse(dateOfBirth));
			 	   s.setEmail(email);
			 	   s.setPhoneNumber(PhoneNumber);
			 	 
			 	    // save it in object 
					
			 	   
			 	   list.add(s);
			    }
			if(list.isEmpty()) {
				throw new PaymentValidationException("Invalid payment ID");
			}
			DBUtil.dbclose();
			return list;
	
	}

	public List<Payment> getPaymentAmount(int id1)  throws SQLException,PaymentValidationException{
		Connection conn = DBUtil.getDBconn() ;
	      List<Payment> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select payment_id , amount from payments where payment_id=?";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	// Attach the value to ?
			pstmt.setInt(1,id1);
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	while(rst.next()) {
				       int paymentId=rst.getInt("payment_id");
				       double amount =rst.getDouble("Amount");
				      
				 	   Payment p = new Payment();
				 	   p.setPaymentId(paymentId);
				 	   p.setAmount(amount);
				 	    // save it in object 
						
				 	   
				 	   list.add(p);
				    }
			 	 if(list.isEmpty()) {
					 throw new PaymentValidationException("Invalid Payment ID");
				 }
				
				DBUtil.dbclose();
				return list;
		
	}

	@Override
	public List<Payment> getPaymentDate(int id2) throws SQLException,PaymentValidationException {
		Connection conn = DBUtil.getDBconn() ;
	      List<Payment> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select payment_id , payment_date from payments where payment_id=?";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	// Attach the value to ?
			pstmt.setInt(1,id2);
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	while(rst.next()) {
				       int paymentId=rst.getInt("payment_id");
				       String paymentDate=rst.getString("payment_date");
				      
				 	   Payment p = new Payment();
				 	   p.setPaymentId(paymentId);
				 	   p.setPaymentDate(LocalDate.parse(paymentDate));
				 	    // save it in object 
						
				 	   
				 	   list.add(p);
				    }
			 if(list.isEmpty()) {
				 throw new PaymentValidationException("Invalid Payment ID");
			 }
				
				DBUtil.dbclose();
				return list;
	}

}