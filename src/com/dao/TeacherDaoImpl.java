package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;
import com.mysql.jdbc.PreparedStatement;
import com.util.DBUtil;

public class TeacherDaoImpl implements TeacherDao {

	public void updateTeacher(int tid, String tfirstName, String tlastName, String temail)
			throws SQLException, TeacherNotFoundException, InvalidTeacherDataException {
		Connection conn = DBUtil.getDBconn();

		String sql = "update teacher set first_name=? ,last_name=? ,email=? where teacher_id =?";
		// prepare the statement

		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// attach the values to ?
		pstmt.setString(1, tfirstName);
		pstmt.setString(2, tlastName);
		pstmt.setString(3, temail);
		pstmt.setInt(4, tid);
		// execute the query
		int rowsUpdated = pstmt.executeUpdate();

		if (rowsUpdated == 0) {
			throw new TeacherNotFoundException("Teacher not found");
		}
		if (tfirstName == null || tfirstName.isEmpty()|| tlastName == null ||tlastName.isEmpty() || temail == null || temail.isEmpty()) {
			throw new InvalidTeacherDataException("Name and email are required.");
		}
		DBUtil.dbclose();

	}

	@Override
	public List<Teacher> displayTeacherInfo()
			throws SQLException, TeacherNotFoundException, InvalidTeacherDataException {
		Connection conn = DBUtil.getDBconn();
		List<Teacher> list = new ArrayList<>();// container
		// fetch data from db
		String sql = "select * from teacher";
		// step 1:prepare the statement to execute
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// step 2:execute the statement and catch in the resultset
		ResultSet rst = pstmt.executeQuery();
		// step 3: read the resultset and Iterate over it to save the object
		while (rst.next()) {
			int teacherId = rst.getInt("teacher_id");
			String firstName = rst.getString("First_name");
			String lastName = rst.getString("Last_name");
			String email = rst.getString("email");

			Teacher t = new Teacher();
			t.setTeacherId(teacherId);
			t.setFirstName(firstName);
			t.setLastName(lastName);
			t.setEmail(email);

			// save it in object

			list.add(t);
			if (firstName == null || lastName.isEmpty() || email == null || email.isEmpty()) {
				throw new InvalidTeacherDataException("Name and email are required.");
			}
		}

		if (list.isEmpty()) {
			throw new TeacherNotFoundException("No Teacher Info to show");
		}
		DBUtil.dbclose();
		return list;

	}

	@Override
	public List<Course> getAssignedCourse(int id) throws SQLException, TeacherNotFoundException {
		List<Course> list = new ArrayList<>();
		Connection conn = DBUtil.getDBconn();
		// step 1:prepare the statement
		String sql = " select course_id , course_name , credits, teacher_teacher_id from courses where teacher_teacher_id=?";
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
		// Attach the value to ?
		pstmt.setInt(1, id);
		// execute the statement
		ResultSet rst = pstmt.executeQuery();
		// Iterate over the result set and read db column
		while (rst.next()) {// if id is present show the data else nullpointerE
			int teacherTeacherId = rst.getInt("teacher_teacher_id");
			int courseId = rst.getInt("course_id");
			String courseName = rst.getString("course_name");
			int credits = rst.getInt("credits");

			Course c = new Course(courseId, courseName, credits, teacherTeacherId);

			list.add(c);

		}
		if (list.isEmpty()) {
			throw new TeacherNotFoundException("No Teacher is assigned to course");
		}
		DBUtil.dbclose();
		return list;
	}

}
