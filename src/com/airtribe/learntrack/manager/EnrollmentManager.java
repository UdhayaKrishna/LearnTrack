package com.airtribe.learntrack.manager;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.ConsoleInputReader;
import com.airtribe.learntrack.util.ConsoleListPrinter;

import java.util.Scanner;

public class EnrollmentManager {
    public static void enrollStudent(Scanner scanner, StudentService studentService, CourseService courseService, EnrollmentService enrollmentService)
            throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter student ID: ");
        int studentId = ConsoleInputReader.readInt(scanner);
        System.out.print("Enter course ID: ");
        int courseId = ConsoleInputReader.readInt(scanner);
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId, studentService, courseService);
        System.out.println("Enrollment created with ID " + enrollment.getId());
    }

    public static void listEnrollmentsForStudent(Scanner scanner, EnrollmentService enrollmentService) throws InvalidInputException {
        System.out.print("Enter student ID: ");
        int studentId = ConsoleInputReader.readInt(scanner);
        ConsoleListPrinter.printList(enrollmentService.getEnrollmentsForStudent(studentId), "No enrollments found for this student.");
    }

    public static void updateEnrollmentStatus(Scanner scanner, EnrollmentService enrollmentService) throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter enrollment ID: ");
        int enrollmentId = ConsoleInputReader.readInt(scanner);
        System.out.print("Enter status (ACTIVE/COMPLETED/CANCELLED): ");
        String statusText = scanner.nextLine().trim().toUpperCase();
        Status status;
        try {
            status = Status.valueOf(statusText.trim().toUpperCase());

        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("'" + statusText + "' is not a valid status option.");
        }
        enrollmentService.markEnrollmentStatus(enrollmentId, status);
        System.out.println("Enrollment status updated.");
    }
}
