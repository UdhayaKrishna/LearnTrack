package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.ConsoleMenu;
import com.airtribe.learntrack.util.CourseManager;
import com.airtribe.learntrack.util.EnrollmentManager;
import com.airtribe.learntrack.util.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        boolean running = true;
        while (running) {
            ConsoleMenu.printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        StudentManager.addStudent(scanner, studentService);
                        break;
                    case "2":
                        StudentManager.listStudents(studentService);
                        break;
                    case "3":
                        StudentManager.searchStudent(scanner, studentService);
                        break;
                    case "4":
                        StudentManager.deactivateStudent(scanner, studentService);
                        break;
                    case "5":
                        CourseManager.addCourse(scanner, courseService);
                        break;
                    case "6":
                        CourseManager.listCourses(courseService);
                        break;
                    case "7":
                        CourseManager.toggleCourseStatus(scanner, courseService);
                        break;
                    case "8":
                        EnrollmentManager.enrollStudent(scanner, studentService, courseService, enrollmentService);
                        break;
                    case "9":
                        EnrollmentManager.listEnrollmentsForStudent(scanner, enrollmentService);
                        break;
                    case "10":
                        EnrollmentManager.updateEnrollmentStatus(scanner, enrollmentService);
                        break;
                    case "11":
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
