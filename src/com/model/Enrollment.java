package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Enrollment {
	private int enrollmentId;
	private LocalDate enrollmentDate;
	private int studentsStudentId;
	private int coursesCourseId;

	public Enrollment() {
	}

	public Enrollment(int enrollmentId, LocalDate enrollmentDate, int studentsStudentId, int coursesCourseId) {
		this.enrollmentId = enrollmentId;
		this.enrollmentDate = enrollmentDate;
		this.studentsStudentId = studentsStudentId;
		this.coursesCourseId = coursesCourseId;
	}

	public Enrollment(LocalDate enrollmentDate, int studentsStudentId, int coursesCourseId) {
		this.enrollmentDate = enrollmentDate;
		this.studentsStudentId = studentsStudentId;
		this.coursesCourseId = coursesCourseId;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public int getStudentsStudentId() {
		return studentsStudentId;
	}

	public void setStudentsStudentId(int studentsStudentId) {
		this.studentsStudentId = studentsStudentId;
	}

	public int getCoursesCourseId() {
		return coursesCourseId;
	}

	public void setCoursesCourseId(int coursesCourseId) {
		this.coursesCourseId = coursesCourseId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coursesCourseId, enrollmentDate, enrollmentId, studentsStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		return coursesCourseId == other.coursesCourseId && Objects.equals(enrollmentDate, other.enrollmentDate)
				&& enrollmentId == other.enrollmentId && studentsStudentId == other.studentsStudentId;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", enrollmentDate=" + enrollmentDate
				+ ", studentsStudentId=" + studentsStudentId + ", coursesCourseId=" + coursesCourseId + "]";
	}
	
	

}
