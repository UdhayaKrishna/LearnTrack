package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class StudentService extends BaseService<Student> {
    private static final List<Student> students = new ArrayList<>();

    public StudentService() {
        super(students);
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        try {
            ValidationUtil.validateName(firstName, "first name");
            ValidationUtil.validateName(lastName, "last name");
            ValidationUtil.validateText(batch, "batch");
            ValidationUtil.validateEmail(email, "email");
            Student student = new Student(IdGenerator.getNextStudentId(), firstName.trim(), lastName.trim(), email.trim(), batch.trim(), true);
            students.add(student);
            return student;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public Student addStudent(String firstName, String lastName, String batch) throws InvalidInputException {
        return addStudent(firstName, lastName, "", batch);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(items);
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        return findById(id, "Student", Student::getId);
    }

    public boolean deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        if (student.isActive()) {
            student.setActive(false);
            return true;
        }
        return false;
    }
}
