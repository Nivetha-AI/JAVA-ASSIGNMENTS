package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.PaymentValidationException;
import com.model.Payment;
import com.model.Student;
import com.service.PaymentService;

public class PaymentServiceTest {
	PaymentService paymentService = new PaymentService();

	@Test
	public void getStudentTest() {
		// use case 1
		List<Student> list = new ArrayList<>();
		int id = 3;
		Student s = new Student(2, "Nithi", "Dev", LocalDate.parse("2002-09-16"), "nithu@gmail.com ", "9867543");
		list.add(s);

		try {
			Assert.assertEquals(list, paymentService.getStudent(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}
		// use case 2
		list = new ArrayList<>();
		id = 10;
		Student s1 = new Student(3, "ram", "kumar", LocalDate.parse("2000-04-09"), "ram@gmail.com", "98754567");
		list.add(s1);

		try {
			Assert.assertEquals(list, paymentService.getStudent(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}

		// use case 3
		list = new ArrayList<>();
		id = 24;
		Student s2 = new Student(4, "visha", "sam", LocalDate.parse("2001-02-15"), "visha@gmail.com", "78889567");
		list.add(s2);

		try {
			Assert.assertEquals(list, paymentService.getStudent(id));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());
		}

	}

	@Test
	public void getPaymentAmountTest() {
		// use case 1
		List<Payment> list = new ArrayList<>();
		int id1 = 2;
		Payment p = new Payment();
		p.setPaymentId(2);
		p.setAmount(500000);
		list.add(p);
		try {
			Assert.assertEquals(list, paymentService.getPaymentAmount(id1));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		list = new ArrayList<>();
		id1 = 10;
		Payment p1 = new Payment();
		p1.setPaymentId(10);
		p1.setAmount(2000000);
		list.add(p1);
		try {
			Assert.assertEquals(list, paymentService.getPaymentAmount(id1));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

		}
		// use case 2
		list = new ArrayList<>();
		id1 = 25;
		Payment p2 = new Payment();
		p2.setPaymentId(10);
		p2.setAmount(2000000);
		list.add(p2);
		try {
			Assert.assertEquals(list, paymentService.getPaymentAmount(id1));
		} catch (SQLException e) {
		} catch (PaymentValidationException e) {
			Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

		}

	}
	@Test
	public void getpaymentDateTest() {
		// use case 1
				List<Payment> list = new ArrayList<>();
				int id2 = 2;
				Payment p = new Payment();
				p.setPaymentId(2);
				p.setPaymentDate(LocalDate.parse("2024-01-04"));
				list.add(p);
				try {
					Assert.assertEquals(list, paymentService.getPaymentDate(id2));
				} catch (SQLException e) {
					
				} catch (PaymentValidationException e) {
					Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

				}
				// use case 2
				list = new ArrayList<>();
			    id2 = 10;
				Payment p1 = new Payment();
				p1.setPaymentId(10);
				p1.setPaymentDate(LocalDate.parse("2024-03-20"));
				list.add(p1);
				try {
					Assert.assertEquals(list, paymentService.getPaymentDate(id2));
				} catch (SQLException e) {
					
				} catch (PaymentValidationException e) {
					Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

				}
				// use case 2
				list = new ArrayList<>();
			    id2 = 25;
				Payment p2 = new Payment();
				p2.setPaymentId(10);
				p2.setPaymentDate(LocalDate.parse("2024-03-20"));
				list.add(p2);
				try {
					Assert.assertEquals(list, paymentService.getPaymentDate(id2));
				} catch (SQLException e) {
					
				} catch (PaymentValidationException e) {
					Assert.assertEquals("Invalid payment ID".toLowerCase(), e.getMessage().toLowerCase());

				}

	}
}
