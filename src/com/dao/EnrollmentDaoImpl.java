package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.InvalidEnrollmentDataException;
import com.model.Course;
import com.model.Student;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class EnrollmentDaoImpl implements EnrollmentDao{

	@Override
	public List<Student> getStudent(int id) throws SQLException ,InvalidEnrollmentDataException{
		List<Student> list = new ArrayList<>();
		Connection conn = DBUtil.getDBconn();
		// step 1:prepare the statement
		String sql = "select * from students s join enrollments e on s.student_id=e.students_student_id"
				+ " where e.enrollment_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        // Attach the value to ?
		pstmt.setInt(1,id);
		// execute the statement
		ResultSet rst =pstmt.executeQuery();
		// Iterate over the result set and read db column 
		while(rst.next()) {
			    
				int StudentId = rst.getInt("student_id");
				String firstName = rst.getString("First_name");
			 	   String lastName = rst.getString("Last_name");
			       String dateOfBirth = rst.getString("Date_of_Birth");
			       String email = rst.getString("email");
			       String PhoneNumber = rst.getString("phone_number");
			       	   
			 	   Student s = new Student();
			 	   s.setStudentId(StudentId);
			 	   s.setFirstName(firstName);
			 	   s.setLastName(lastName);
			 	   s.setDateOfBirth(LocalDate.parse(dateOfBirth));
			 	   s.setEmail(email);
			 	   s.setPhoneNumber(PhoneNumber);
			
				
				
				
				list.add(s);
				
				}
		if(list.isEmpty()) {
			throw new InvalidEnrollmentDataException("Invalid Enrollment ID");
		}
		DBUtil.dbclose();
		return list;
		
	}

	public List<Course> getCourses(int id1) throws SQLException,InvalidEnrollmentDataException {
		
		List<Course> list = new ArrayList<>();
		Connection conn = DBUtil.getDBconn();
		// step 1:prepare the statement
		String sql = "select * from courses c  join enrollments e on c.course_id=e.courses_course_id"
				+ " where e.enrollment_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        // Attach the value to ?
		pstmt.setInt(1,id1);
		// execute the statement
		ResultSet rst =pstmt.executeQuery();
		// Iterate over the result set and read db column 
		while(rst.next()) {// if id is present show the data else nullpointerE
			    
				int CourseId = rst.getInt("course_id");
				String courseName = rst.getString("course_name");
			 	int credits = rst.getInt("credits");
			       	   
			 	   Course c= new Course();
			 	   c.setCourseId(CourseId);
			 	   c.setCourseName(courseName);
			 	   c.setCredits(credits);
		
				
				list.add(c);
				
				}
		if(list.isEmpty()) {
			throw new InvalidEnrollmentDataException("Invalid Enrollment ID");
		}
		DBUtil.dbclose();
		return list;	
		
			
	}

}
