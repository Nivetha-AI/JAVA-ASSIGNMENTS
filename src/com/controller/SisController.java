package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.dto.CourseStatisticsDto;
import com.exception.CourseNotFoundException;
import com.exception.PaymentValidationException;
import com.model.Course;
import com.model.Enrollment;
import com.model.Payment;
import com.model.Student;
import com.service.SisService;

public class SisController {

	public static void main(String[] args) {
		SisService sisService = new SisService();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("************Student Info System ************");
			System.out.println("Press 1. to enroll a student in course ");
			System.out.println("Press 2. to Assigns a teacher to a course ");
			System.out.println("Press 3. to Records a payment made by a student.");
			System.out.println("press 4. to Generates a report of students enrolled in a specific course");
			System.out.println("press 5  to Generates a report of payments made by a specific student. ");
			System.out.println("press 6. to Calculates statistics for a specific course, such as the number of enrollments and total payments.");
			System.out.println("Press 0. to Exit");
			System.out.println("**********************************************");

			int input = sc.nextInt();
			// what if user types 0 and wants to exit ??
			if (input == 0) {
				System.out.println("Exiting ... Thank You");
				break;
			}

			switch (input) {
			case 1:
				System.out.println("Enter Student ID ");
				int studentsStudentId = sc.nextInt();
				System.out.println("Enter course ID ");
				int coursesCourseId = sc.nextInt();

				// insert the record in db
				Enrollment enrl = new Enrollment(LocalDate.now(), studentsStudentId, coursesCourseId);

				try {
					sisService.enrollmentInCourse(enrl);
					System.out.println("Enrolled a Student in course ");

				} catch (SQLException |  CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2:
				System.out.println("Enter course name ");
				String courseName = sc.next();
				System.out.println("Enter credits to the course ");
				int credits = sc.nextInt();
				System.out.println("Enter Teacher ID");
				int teacherTeacherId = sc.nextInt();

				// insert the record in db
				Course c = new Course(courseName, credits, teacherTeacherId);

				try {
					sisService.assignTeacherToCourse(c);
					System.out.println("Assigned teacher to course ");

				} catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 3:
				System.out.println("Enter Amount ");
				double amount = sc.nextDouble();
				System.out.println("Enter student Id to pay ");
				int studentStudentId = sc.nextInt();

				// insert the record in db
				Payment pay = new Payment(amount, LocalDate.now(), studentStudentId);

				try {
					sisService.recordPayment(pay);
					System.out.println("Payment is made by student ");
				} catch (SQLException | PaymentValidationException e) {
					System.out.println(e.getMessage());
				}

				break;
			case 4:
				System.out.println("**********list of Students enrolled for a course  *********************");
				System.out.println("Enter course ID : ");
				int id1 = sc.nextInt();

				try {
					List<Student> list = sisService.generateEnrollmentReport(id1);
					System.out.println("student_id" + "       " + "FirstName" + "        " + "LastName" + "     "
							+ "Email" + "               " + "phonenumber");
					for (Student s : list) {
						System.out.println(s.getStudentId() + "  \t\t" + s.getFirstName() + " \t\t  " + s.getLastName()
								+ " \t" + s.getEmail() + "  \t\t " + s.getPhoneNumber());
					}
				}

				catch (SQLException | CourseNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("**********List of Students Associated with payments  ********************");
				System.out.println("Enter Payment ID : ");
				int id = sc.nextInt();

				try {
					List<Student> list = sisService.generatePaymentReport(id);
					System.out.println("student_id" + "       " + "first_name" + "        " + "last_name" + "   "
							+ "     " + "DOB" + " \t\t " + "email" + "\t \t" + "phoneNumber");
					for (Student s : list) {
						System.out.println(
								s.getStudentId() + "\t \t " + s.getFirstName() + "\t\t  " + s.getLastName() + "\t\t  "
										+ s.getDateOfBirth() + "\t\t " + s.getEmail() + " \t\t " + s.getPhoneNumber());
					}

				} catch (SQLException | PaymentValidationException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter course Id : ");
				int couid = sc.nextInt();
				
				try {
					List<CourseStatisticsDto> list = sisService.calculateCourseStatistics(couid);

					for (CourseStatisticsDto cous : list) {
						System.out.println(" Course ID: "+cous.getCourseId()+"\t"+"Enrollment count:  "+cous.getEnrollmentCount()+"\t"+
					"Total payment: "+cous.getTotalPayment()/100000 + "L");
				}} catch (SQLException | CourseNotFoundException  e) {
					System.out.println(e.getMessage());
				}

				break;
			default:
				break;

			}
		}
		sc.close();
	}
}