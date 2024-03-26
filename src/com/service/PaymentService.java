package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.PaymentDaoImpl;
import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;

public class PaymentService {
    PaymentDaoImpl paymentDao=new PaymentDaoImpl();

	public List<Student> getStudent(int id) throws SQLException, PaymentValidationException{
		List <Student> list = paymentDao.getStudent(id);
		return list;
	}

	public List<Payment> getPaymentAmount(int id1)  throws SQLException,PaymentValidationException{
		List<Payment> list = paymentDao.getPaymentAmount(id1);
		return list;
	}

	public List<Payment> getPaymentDate(int id2) throws SQLException,PaymentValidationException{
		List<Payment> list = paymentDao.getPaymentDate(id2);

		return list;
	}

}
