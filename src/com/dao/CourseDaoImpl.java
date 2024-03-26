package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.CourseNotFoundException;

import com.model.Course;
import com.model.Student;
import com.model.Teacher;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class CourseDaoImpl implements CourseDao{

	public void assignTeacher(Course c) throws SQLException,CourseNotFoundException{
		Connection conn = DBUtil.getDBconn() ;
		String sql = "insert into courses (course_name ,credits , teacher_teacher_id) values (?,?,?)";
		// prepare the statement
   	  PreparedStatement pstmt =  (PreparedStatement) conn.prepareStatement(sql);
	  // attach the values to ?
	      pstmt.setString(1,c.getCourseName());
	      pstmt.setInt(2,c.getCredits());
	      pstmt.setInt(3,c.getTeacherTeacherId());
	
		
    // execute the query
	      int rowsinserted =pstmt.executeUpdate();// 1:success ,0:failure
			if(rowsinserted==0) {
			      throw new CourseNotFoundException(" Teacher is not Assigned to the course");
			}	
	DBUtil.dbclose();
		
	}

	public void updateCourseInfo(int cid, String ccoursename, int ccredits, int cteacherId) throws SQLException ,CourseNotFoundException {
		
		Connection conn = DBUtil.getDBconn() ;
		
		String sql ="update courses set course_name=? ,credits=? ,teacher_teacher_id=? where course_id =?";
		// prepare the statement
		
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			// attach the values to ?
			pstmt.setString(1, ccoursename);
			pstmt.setInt(2,ccredits);
			pstmt.setInt(3,cteacherId);
			pstmt.setInt(4, cid);
			// execute the query
			 int rowsUpdated = pstmt.executeUpdate();
			
			if (rowsUpdated == 0) {
	            throw new CourseNotFoundException("Course not found");
	        }

		    DBUtil.dbclose();   
			

	}

	@Override
	public List<Course> displayCourseInfo() throws SQLException, CourseNotFoundException {
		
			Connection conn = DBUtil.getDBconn() ;
		      List<Course> list = new ArrayList<>();//container
		   // fetch data from db 
		      String sql ="select * from courses";
		    //step 1:prepare the statement to execute
		 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);	 	 
				 // step 2:execute the statement and catch in the resultset
				 	 ResultSet rst = pstmt.executeQuery();
				 	 
				 //step 3: read the resultset and Iterate over it to save the object
				    while(rst.next()) {
				 	   int CourseId = rst.getInt("course_id");
				 	   String courseName = rst.getString("course_name");
				 	   int credits = rst.getInt("credits");
				       int teacherTeacherId=rst.getInt("teacher_teacher_id");       	   
				 	   Course c = new Course(CourseId,courseName , credits , teacherTeacherId) ; 
				 	   // save it in object 
						
				 	   
				 	   list.add(c);
				    }
				 if(list.isEmpty()) {
					 throw new CourseNotFoundException("No Courses to Display");
				 }
				    DBUtil.dbclose();
					return list;
	}

	@Override
	public List<Teacher> getTeacher(int id) throws SQLException ,CourseNotFoundException{
		Connection conn = DBUtil.getDBconn() ;
	      List<Teacher> list = new ArrayList<>();//container
	   // fetch data from db 
	      String sql ="select * from teacher t join courses c on t.teacher_id = c.teacher_teacher_id where course_id=?";
	    //step 1:prepare the statement to execute
	 	 PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
	 	// Attach the value to ?
			pstmt.setInt(1,id);
			 // step 2:execute the statement and catch in the resultset
			 	 ResultSet rst = pstmt.executeQuery();
			 	 
			 //step 3: read the resultset and Iterate over it to save the object
			    while(rst.next()) {
			       int teacherId=rst.getInt("teacher_id");
			       String FirstName = rst.getString("first_name");
			       String LastName = rst.getString("Last_name");
			       String email =rst.getString("email");
			       
			 	   Teacher t= new Teacher(teacherId , FirstName , LastName , email);
			 	   
			 	   
			 	    // save it in object 
					
			 	   
			 	   list.add(t);
			    }
			if(list.isEmpty()) {
			      throw new CourseNotFoundException(" No Teacher is Assigned to course");
			}
			
			DBUtil.dbclose();
			return list;
		
	}

	public List<Student> getEnrollments(int id1) throws SQLException, CourseNotFoundException {
		List<Student> list = new ArrayList<>();
		Connection conn = DBUtil.getDBconn();
		// step 1:prepare the statement
		String sql = "select * from students s join enrollments e on s.student_id=e.students_student_id where courses_course_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        // Attach the value to ?
		pstmt.setInt(1,id1);
		// execute the statement
		ResultSet rst =pstmt.executeQuery();
		// Iterate over the result set and read db column 
		while(rst.next()) {// if id is present show the data else nullpointerE
			    
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
			 throw new CourseNotFoundException("Course ID is not found");
		 }
		DBUtil.dbclose();
		return list;
	
	}

}
