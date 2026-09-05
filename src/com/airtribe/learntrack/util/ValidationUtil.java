package com.airtribe.learntrack.util;

import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"
    );

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s'-]{2,50}$");


    public static void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
    }

    public static void validateEmail(String value, String fieldName) {
        validateText(value, fieldName);
        if (!EMAIL_PATTERN.matcher(value).matches()) throw new IllegalArgumentException(fieldName + " format invalid.");
    }

    public static void validateStudent(StudentService studentService, int id, String fieldName) throws EntityNotFoundException {
        if (!studentService.findStudentById(id).isActive())
            throw new IllegalArgumentException(fieldName + " is not ACTIVE.");
    }

    public static void validateCourse(CourseService courseService, int id, String fieldName) throws EntityNotFoundException {
        if (!courseService.findCourseById(id).isActive())
            throw new IllegalArgumentException(fieldName + " is not ACTIVE.");
    }

    public static void validateName(String value, String fieldName) {
        validateText(value, fieldName);
        if (!NAME_PATTERN.matcher(value).matches()) throw new IllegalArgumentException(fieldName + " format invalid.");
    }

    public static void validatePositiveInteger(int value, String fieldName) {
        if (value <= 0) throw new IllegalArgumentException(fieldName + " must be greater than zero.");
    }

    public static void validateDuplicateEnrollment(int studentId, int courseId, EnrollmentService enrollmentService, String fieldName) {
        if (enrollmentService.getEnrollmentsForStudent(studentId).stream()
                .filter(enrollment -> enrollment.getCourseId() == courseId)
                .anyMatch(enrollment -> enrollment.getStatus() == Status.ACTIVE))
            throw new IllegalArgumentException(fieldName + " already exists and active for same course.");
    }
}
