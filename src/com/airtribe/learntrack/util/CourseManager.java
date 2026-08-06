package com.airtribe.learntrack.util;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;

import java.util.Scanner;

public class CourseManager {
    public static void addCourse(Scanner scanner, CourseService courseService) throws InvalidInputException {
        System.out.print("Course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Duration in weeks: ");
        int duration = ConsoleInputReader.readInt(scanner);

        Course course = courseService.addCourse(courseName, description, duration);
        System.out.println("Course added successfully with ID " + course.getId());
    }

    public static void listCourses(CourseService courseService) {
        ConsoleListPrinter.printList(courseService.listCourses(), "No courses available.");
    }

    public static void toggleCourseStatus(Scanner scanner, CourseService courseService) throws EntityNotFoundException {
        System.out.print("Enter course ID: ");
        int id = ConsoleInputReader.readInt(scanner);
        System.out.print("Activate or deactivate? (A/D): ");
        String choice = scanner.nextLine().trim().toUpperCase();
        if ("A".equals(choice)) {
            courseService.activateCourse(id);
            System.out.println("Course activated.");
        } else if ("D".equals(choice)) {
            courseService.deactivateCourse(id);
            System.out.println("Course deactivated.");
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
