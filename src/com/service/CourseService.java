package com.service;


import java.sql.SQLException;
import java.util.List;

import com.dao.CourseDaoImpl;
import com.exception.CourseNotFoundException;
import com.model.Course;
import com.model.Student;
import com.model.Teacher;

public class CourseService {
	CourseDaoImpl courseDao = new CourseDaoImpl();
	public void assignTeacher(Course c) throws SQLException ,CourseNotFoundException{
		courseDao.assignTeacher(c);
		
	}
	public void updateCourseInfo(int cid, String ccoursename, int ccredits, int cteacherId) throws SQLException ,CourseNotFoundException {
		courseDao.updateCourseInfo(cid ,ccoursename,ccredits ,cteacherId);
		
	}
	public List<Course> displayCourseInfo()throws SQLException ,CourseNotFoundException{
		List <Course> list = courseDao.displayCourseInfo();
		return list;
	}
	public List<Teacher> getTeacher(int id)throws SQLException,CourseNotFoundException {
		List<Teacher> list = courseDao.getTeacher(id);
		return list;
	}
	public List<Student> getEnrollments(int id1) throws SQLException , CourseNotFoundException{
		List<Student> list=courseDao.getEnrollments(id1);
		return list;
	}

}
