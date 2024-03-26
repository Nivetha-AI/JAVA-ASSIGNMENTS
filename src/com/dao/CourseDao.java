package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.CourseNotFoundException;
import com.model.Course;
import com.model.Student;
import com.model.Teacher;

public interface CourseDao {
	public void assignTeacher(Course c) throws SQLException ,CourseNotFoundException;
	public void updateCourseInfo(int cid, String ccoursename, int ccredits, int cteacherId)throws SQLException ,CourseNotFoundException  ;
	public List<Course> displayCourseInfo()throws SQLException , CourseNotFoundException;
	public List<Teacher> getTeacher(int id)throws SQLException,CourseNotFoundException ;
	public List<Student> getEnrollments(int id1) throws SQLException , CourseNotFoundException;



}
