package com.dto;

import java.util.Objects;

public class CourseStatisticsDto {
  private int courseId;
  private int enrollmentCount;
  private double totalPayment;
public CourseStatisticsDto() {
}
public CourseStatisticsDto(int courseId, int enrollmentCount, double totalPayment) {
	this.courseId = courseId;
	this.enrollmentCount = enrollmentCount;
	this.totalPayment = totalPayment;
}
public CourseStatisticsDto(int enrollmentCount, double totalPayment) {
	this.enrollmentCount = enrollmentCount;
	this.totalPayment = totalPayment;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getEnrollmentCount() {
	return enrollmentCount;
}
public void setEnrollmentCount(int enrollmentCount) {
	this.enrollmentCount = enrollmentCount;
}
public double getTotalPayment() {
	return totalPayment;
}
public void setTotalPayment(double totalPayment) {
	this.totalPayment = totalPayment;
}

@Override
public int hashCode() {
	return Objects.hash(courseId, enrollmentCount, totalPayment);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CourseStatisticsDto other = (CourseStatisticsDto) obj;
	return courseId == other.courseId && enrollmentCount == other.enrollmentCount
			&& Double.doubleToLongBits(totalPayment) == Double.doubleToLongBits(other.totalPayment);
}
@Override
public String toString() {
	return "CourseStatisticsDto [courseId=" + courseId + ", enrollmentCount=" + enrollmentCount + ", totalPayment="
			+ totalPayment + "]";
}


  
}
