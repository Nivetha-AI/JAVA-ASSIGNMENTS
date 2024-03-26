package com.service;


import java.sql.SQLException;
import java.util.List;

import com.dao.TeacherDaoImpl;
import com.exception.InvalidTeacherDataException;
import com.exception.TeacherNotFoundException;
import com.model.Course;
import com.model.Teacher;

public class TeacherService {
	TeacherDaoImpl teacherDao = new TeacherDaoImpl();


	public void updateTeacher(int tid , String tfirstName, String tlastName, String temail)throws SQLException,TeacherNotFoundException , InvalidTeacherDataException {
		
		teacherDao.updateTeacher(tid, tfirstName,tlastName,temail);
	}


	public  List<Teacher> displayTeacherInfo()throws SQLException,TeacherNotFoundException, InvalidTeacherDataException{
		List <Teacher> list = teacherDao.displayTeacherInfo();
		return list;
		
	}

	public List<Course> getAssignedCourse(int id) throws SQLException,TeacherNotFoundException{
		List<Course> list= teacherDao.getAssignedCourse(id);
		return list;
	}

}
