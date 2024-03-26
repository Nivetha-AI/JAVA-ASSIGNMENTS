package com.model;

import java.util.Objects;

public class Teacher {
   private int teacherId;
   private String firstName;
   private String lastName;
   private String email;
public Teacher() {}
public Teacher(int teacherId, String firstName, String lastName, String email) {
	this.teacherId = teacherId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
}
public Teacher(String firstName, String lastName, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
}
public int getTeacherId() {
	return teacherId;
}
public void setTeacherId(int teacherId) {
	this.teacherId = teacherId;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}


@Override
public int hashCode() {
	return Objects.hash(email, firstName, lastName, teacherId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Teacher other = (Teacher) obj;
	return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
			&& Objects.equals(lastName, other.lastName) && teacherId == other.teacherId;
}
@Override
public String toString() {
	return "Teacher [teacherId=" + teacherId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
			+ email + "]";
}
  
   
}
