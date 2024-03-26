package com.model;

import java.util.Objects;

public class Course {
  private int courseId;
  private String courseName;
  private int credits;
  private int teacherTeacherId;
public Course() {}
public Course(int courseId, String courseName, int credits, int teacherTeacherId) {
	this.courseId = courseId;
	this.courseName = courseName;
	this.credits = credits;
	this.teacherTeacherId = teacherTeacherId;
}
public Course(String courseName, int credits, int teacherTeacherId) {
	this.courseName = courseName;
	this.credits = credits;
	this.teacherTeacherId = teacherTeacherId;
}




public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public int getCredits() {
	return credits;
}
public void setCredits(int credits) {
	this.credits = credits;
}
public int getTeacherTeacherId() {
	return teacherTeacherId;
}
public void setTeacherTeacherId(int teacherTeacherId) {
	this.teacherTeacherId = teacherTeacherId;
}


@Override
public int hashCode() {
	return Objects.hash(courseId, courseName, credits, teacherTeacherId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Course other = (Course) obj;
	return courseId == other.courseId && Objects.equals(courseName, other.courseName) && credits == other.credits
			&& teacherTeacherId == other.teacherTeacherId;
}
@Override
public String toString() {
	return "Course [courseId=" + courseId + ", courseName=" + courseName + ", credits=" + credits
			+ ", teacherTeacherId=" + teacherTeacherId + "]";
}


  
  
}
