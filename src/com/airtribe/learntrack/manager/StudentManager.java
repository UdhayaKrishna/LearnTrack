package com.airtribe.learntrack.manager;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.ConsoleInputReader;
import com.airtribe.learntrack.util.ConsoleListPrinter;

import java.util.Scanner;

public class StudentManager {
    public static void addStudent(Scanner scanner, StudentService studentService) throws InvalidInputException {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Batch: ");
        String batch = scanner.nextLine();

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added successfully with ID " + student.getId());
    }

    public static void listStudents(StudentService studentService) {
        ConsoleListPrinter.printList(studentService.listStudents(), "No students available.");
    }

    public static void searchStudent(Scanner scanner, StudentService studentService) throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter student ID: ");
        int id = ConsoleInputReader.readInt(scanner);
        Student student = studentService.findStudentById(id);
        System.out.println(student);
    }

    public static void deactivateStudent(Scanner scanner, StudentService studentService) throws EntityNotFoundException, InvalidInputException {
        System.out.print("Enter student ID to deactivate: ");
        int id = ConsoleInputReader.readInt(scanner);
        if (studentService.deactivateStudent(id)) {
            System.out.println("Student already deactivated");
        } else {
            System.out.println("Student deactivated.");
        }
    }
}
