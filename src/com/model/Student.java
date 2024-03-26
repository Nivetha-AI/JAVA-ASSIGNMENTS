package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
  private int studentId;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private  String email;
  private String phoneNumber;
public Student() {}
public Student(int studentId, String firstName, String lastName,LocalDate dateOfBirth, String email, String phoneNumber) {
	this.studentId = studentId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.email = email;
	this.phoneNumber = phoneNumber;
}
public Student(String firstName, String lastName,LocalDate dateOfBirth, String email, String phoneNumber) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.email = email;
	this.phoneNumber = phoneNumber;
}
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public LocalDate getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(LocalDate dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


@Override
public int hashCode() {
	return Objects.hash(dateOfBirth, email, firstName, lastName, phoneNumber, studentId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Student other = (Student) obj;
	return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email, other.email)
			&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
			&& Objects.equals(phoneNumber, other.phoneNumber) && studentId == other.studentId;
}
@Override
public String toString() {
	return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
			+ dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
}

  
  
}
