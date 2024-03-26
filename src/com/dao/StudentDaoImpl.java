package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.exception.InvalidStudentDataException;
import com.exception.PaymentValidationException;
import com.exception.StudentNotFoundException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class StudentDaoImpl implements StudentDao {
	
	public List<Student> displayStudentInfo() throws SQLException,StudentNotFoundException, InvalidStudentDataException {
		Connection conn = DBUtil.getDBconn() ;
	      List<Student> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select * from students";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		 	 
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	 
			 //step 3: read the resultset and Iterate over it to save the object
			    while(rst.next()) {
			 	   int studentId = rst.getInt("student_id");
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
			 	  if ( !email.endsWith("@gmail.com") || LocalDate.now().isBefore(LocalDate.parse(dateOfBirth))) {
			 	        throw new InvalidStudentDataException("Invalid student data provided.");
			 	    }
			    }
			 if(list.isEmpty()) {
				 throw new StudentNotFoundException("No students to Display");
			 }
			
			DBUtil.dbclose();
			return list;
	}

	public void enrollmentInCourse(Enrollment enrl) throws SQLException, CourseNotFoundException {
		Connection conn = DBUtil.getDBconn() ;
		String sql = "insert into enrollments (enrollment_date , students_student_id, courses_course_id) values (?,?,?)";
		// prepare the statement
   	  PreparedStatement pstmt =  (PreparedStatement) conn.prepareStatement(sql);
	  // attach the values to ?
	pstmt.setString(1,enrl.getEnrollmentDate().toString());
	pstmt.setInt(2,enrl.getStudentsStudentId());
	pstmt.setInt(3,enrl.getCoursesCourseId());
		
    // execute the query
	int rowinserted=pstmt.executeUpdate();//1:success ,0:failure
	if(rowinserted==0) {
	      throw new CourseNotFoundException(" Student is not enrolled in the course");
	}
	
	
	DBUtil.dbclose();
		
	}

	public void updateStudentInfo(int eid, String efname, String elname, LocalDate edob, String eemail,
			String ephonenumber) throws SQLException, StudentNotFoundException, InvalidStudentDataException {
		 Connection conn = DBUtil.getDBconn() ;
			
			String sql ="update students set first_name=? ,last_name=? , date_of_birth=? ,"
					+ "  email=?,phone_number=?  where student_id =?";
			// prepare the statement
			
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
				// attach the values to ?
				pstmt.setString(1, efname);
				pstmt.setString(2,elname);
				pstmt.setDate(3, java.sql.Date.valueOf(edob));
				pstmt.setString(4,eemail);
				pstmt.setString(5, ephonenumber);  
				pstmt.setInt(6,eid);
				// execute the query
				 int rowsUpdated = pstmt.executeUpdate();
				
				if (rowsUpdated == 0) {
		            throw new StudentNotFoundException("Student not found");
		        }
				if ( !eemail.endsWith("@gmail.com") || LocalDate.now().isBefore(edob)) {
		 	        throw new InvalidStudentDataException("Invalid student data provided");
		 	    }

			    DBUtil.dbclose();   
				
		
	}

	public void makePayment(Payment pay) throws SQLException,PaymentValidationException {
		Connection conn = DBUtil.getDBconn() ;
		String sql = "insert into payments (amount,payment_date , students_student_id) values (?,?,?)";
		// prepare the statement
   	  PreparedStatement pstmt =  (PreparedStatement) conn.prepareStatement(sql);
	  // attach the values to ?
	pstmt.setDouble(1,pay.getAmount());
	pstmt.setString(2,pay.getPaymentDate().toString());
	pstmt.setInt(3,pay.getStudentsStudentId());
	
		
    // execute the query
	int rowinserted=pstmt.executeUpdate();//1:success ,0:failure
	if(rowinserted==0) {
	      throw new PaymentValidationException(" Payment is not done ");
	}
	
	
	DBUtil.dbclose();
		
	}


	@Override
	public List<Course> getEnrolledCourses(int id1) throws SQLException,StudentNotFoundException {
		Connection conn = DBUtil.getDBconn() ;
	      List<Course> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select * from courses c join enrollments e on c.course_id=e.courses_course_id where students_student_id=?";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	// Attach the value to ?
			pstmt.setInt(1,id1);
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	 
			 //step 3: read the resultset and Iterate over it to save the object
			    while(rst.next()) {
			       int courseId=rst.getInt("course_id");
			       String courseName = rst.getString("course_name");
			       int credits = rst.getInt("credits");
					

			 	   Course c = new Course();
			 	   c.setCourseId(courseId);
			 	   c.setCourseName(courseName);
			 	   c.setCredits(credits);
		
			 	   
			 	    // save it in object 
					
			 	   
			 	   list.add(c);
			    }
			    if(list.isEmpty()) {
					 throw new StudentNotFoundException("Invalid Student Id ");
				 }
				
			
			DBUtil.dbclose();
			return list;
	}

	public List<Payment> getPaymentHistory(int id) throws SQLException ,StudentNotFoundException{
		List<Payment> list = new ArrayList<>();
		Connection conn = DBUtil.getDBconn();
		// step 1:prepare the statement
		String sql = "select * from payments where students_student_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        // Attach the value to ?
		pstmt.setInt(1,id);
		// execute the statement
		ResultSet rst =pstmt.executeQuery();
		// Iterate over the result set and read db column 
		while(rst.next()) {// if id is present show the data else nullpointerE
				int studentsStudentId = rst.getInt("students_student_id");
				int paymentId=rst.getInt("payment_id");
				double amount = rst.getDouble("amount");
				LocalDate paymentDate = LocalDate.parse(rst.getString("payment_date"));
				// save it in object 
				
				Payment p = new Payment( paymentId ,amount, paymentDate,studentsStudentId);
				
				
				list.add(p);
				
				}
		if(list.isEmpty()) {
			 throw new StudentNotFoundException("Invalid Student Id ");
		 }
		
		DBUtil.dbclose();
		return list;
		
	}

	
	



}
