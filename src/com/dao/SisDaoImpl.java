package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dto.CourseStatisticsDto;
import com.exception.CourseNotFoundException;
import com.exception.PaymentValidationException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class SisDaoImpl implements SisDao {

	public void enrollmentInCourse(Enrollment enrl) throws SQLException, CourseNotFoundException {
		Connection conn = DBUtil.getDBconn();
		
		String sql = "insert into enrollments (enrollment_date , students_student_id, courses_course_id) values (?,?,?)";
		// prepare the statement
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// attach the values to ?
		pstmt.setString(1, enrl.getEnrollmentDate().toString());
		pstmt.setInt(2, enrl.getStudentsStudentId());
		pstmt.setInt(3, enrl.getCoursesCourseId());

		// execute the query
		int rowsinserted =pstmt.executeUpdate();// 1:success ,0:failure
		if(rowsinserted==0) {
		      throw new CourseNotFoundException(" Student is not enrolled in the course");
		}
		DBUtil.dbclose();

	}

	@Override
	public void assignTeacherToCourse(Course c) throws SQLException, CourseNotFoundException {
		Connection conn = DBUtil.getDBconn();
		String sql = "insert into courses (course_name ,credits , teacher_teacher_id) values (?,?,?)";
		// prepare the statement
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// attach the values to ?
		pstmt.setString(1, c.getCourseName());
		pstmt.setInt(2, c.getCredits());
		pstmt.setInt(3, c.getTeacherTeacherId());

		// execute the query
		int rowinserted=pstmt.executeUpdate();// 1:success ,0:failure
		if(rowinserted==0) {
		      throw new CourseNotFoundException("Teacher is not Assigned to the course");
		}
		DBUtil.dbclose();

	}

	@Override
	public void recordPayment(Payment pay) throws SQLException, PaymentValidationException {
		Connection conn = DBUtil.getDBconn();
		String sql = "insert into payments (amount,payment_date , students_student_id) values (?,?,?)";
		// prepare the statement
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// attach the values to ?
		pstmt.setDouble(1, pay.getAmount());
		pstmt.setString(2, pay.getPaymentDate().toString());
		pstmt.setInt(3, pay.getStudentsStudentId());

		// execute the query
		int rowsinserted=pstmt.executeUpdate();// 1:success ,0:failure
		if(rowsinserted==0) {
		      throw new PaymentValidationException("payment is not recorded");
		}
		DBUtil.dbclose();

	}

	@Override
	public List<Student> generatePaymentReport(int id) throws SQLException, PaymentValidationException {
		Connection conn = DBUtil.getDBconn();
		List<Student> list = new ArrayList<>();// container
		// fetch data from db
		String sql = "select * from students s join payments p on s.student_id =p.students_student_id where payment_id=?";
		// step 1:prepare the statement to execute
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// Attach the value to ?
		pstmt.setInt(1, id);
		// step 2:execute the statement and catch in the resultset
		ResultSet rst = pstmt.executeQuery();

		// step 3: read the resultset and Iterate over it to save the object
		while (rst.next()) {
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
		}
       if(list.isEmpty()) {
		      throw new PaymentValidationException("Invalid Payment ID");

       }
		DBUtil.dbclose();
		return list;

	}
	public List<Student>generateEnrollmentReport(int id1) throws SQLException ,CourseNotFoundException{
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
		      throw new CourseNotFoundException("Invalid Course ID");

      }
		DBUtil.dbclose();
		return list;
	
	}

	@Override
	public List<CourseStatisticsDto> calculateCourseStatistics(int couid) throws SQLException ,CourseNotFoundException{
		Connection conn = DBUtil.getDBconn() ;
	      List<CourseStatisticsDto> list = new ArrayList<>();//container
		// step 1:prepare the statement
		String sql = "select course_id ,count(enrollment_id) AS Enrollment_Count , sum(amount) as Total_Payment "
				+ "from courses c join  enrollments e on c.course_id = e.courses_course_id "
				+ "join students s on s.student_id = e.Students_student_id join "
				+ "payments p on p.students_student_id =s.student_id where c.course_id=?";
				
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      // Attach the value to ?
		pstmt.setInt(1,couid);
		// execute the statement
		ResultSet rst =pstmt.executeQuery();
		// Iterate over the result set and read db column 
				if(rst.next()) {
			    int courseId =rst.getInt("course_Id");
			    int EnrollmentCount=rst.getInt("Enrollment_Count");
			    double TotalPayment =rst.getDouble("Total_payment");
				
				// save it in object 
			 	CourseStatisticsDto courstat=new CourseStatisticsDto(courseId,EnrollmentCount, TotalPayment);
				
				list.add(courstat);
				}
				 if(list.isEmpty()) {
				      throw new CourseNotFoundException("Invalid Course ID");

		      }
		
		DBUtil.dbclose();
		
		

		return list;
		
		
		
	}

}
