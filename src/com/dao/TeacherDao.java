package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;

public interface TeacherDao {
	
	public void updateTeacher(int tid,String tfirstName, String tlastName, String temail)throws SQLException,TeacherNotFoundException , InvalidTeacherDataException ;
	public  List<Teacher> displayTeacherInfo()throws SQLException ,TeacherNotFoundException, InvalidTeacherDataException;
	public List<Course> getAssignedCourse(int id)throws SQLException,TeacherNotFoundException ;
}
