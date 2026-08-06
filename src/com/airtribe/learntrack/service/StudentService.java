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
        super();
        this.items.addAll(students);
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) throws InvalidInputException {
        try {
            ValidationUtil.validateName(firstName, "first name");
            ValidationUtil.validateName(lastName, "last name");
            ValidationUtil.validateText(batch, "batch");
            Student student = new Student(IdGenerator.getNextStudentId(), firstName.trim(), lastName.trim(), email.trim(), batch.trim(), true);
            items.add(student);
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
        return items;
    }

    public Student findStudentById(int id) throws EntityNotFoundException {
        return findById(items, id, "Student", Student::getId);
    }

    public void deactivateStudent(int id) throws EntityNotFoundException {
        Student student = findStudentById(id);
        student.setActive(false);
    }
}
