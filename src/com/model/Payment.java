package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Payment {
  private int paymentId;
  private double amount ;
  private  LocalDate paymentDate;
  private int studentsStudentId;
public Payment() {
}
public Payment(int paymentId, double amount, LocalDate paymentDate, int studentsStudentId) {
	this.paymentId = paymentId;
	this.amount = amount;
	this.paymentDate = paymentDate;
	this.studentsStudentId = studentsStudentId;
}
public Payment(double amount, LocalDate paymentDate, int studentsStudentId) {
	this.amount = amount;
	this.paymentDate = paymentDate;
	this.studentsStudentId = studentsStudentId;
}
public int getPaymentId() {
	return paymentId;
}
public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public LocalDate getPaymentDate() {
	return paymentDate;
}
public void setPaymentDate(LocalDate paymentDate) {
	this.paymentDate = paymentDate;
}
public int getStudentsStudentId() {
	return studentsStudentId;
}
public void setStudentsStudentId(int studentsStudentId) {
	this.studentsStudentId = studentsStudentId;
}


@Override
public int hashCode() {
	return Objects.hash(amount, paymentDate, paymentId, studentsStudentId);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Payment other = (Payment) obj;
	return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
			&& Objects.equals(paymentDate, other.paymentDate) && paymentId == other.paymentId
			&& studentsStudentId == other.studentsStudentId;
}
@Override
public String toString() {
	return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", paymentDate=" + paymentDate
			+ ", studentsStudentId=" + studentsStudentId + "]";
}
  
  
  
}
