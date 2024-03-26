package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;

public interface PaymentDao {
	public List<Student> getStudent(int id) throws SQLException , PaymentValidationException;
	public List<Payment> getPaymentAmount(int id1) throws SQLException ,PaymentValidationException;
	public List<Payment> getPaymentDate(int id2) throws SQLException,PaymentValidationException;

}
