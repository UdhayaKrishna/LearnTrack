package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Status;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService extends BaseService<Enrollment> {
    private static final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentService() {
        super();
        this.items.addAll(enrollments);
    }

    public Enrollment enrollStudent(int studentId, int courseId, StudentService studentService, CourseService courseService)
            throws EntityNotFoundException {
        studentService.findStudentById(studentId);
        courseService.findCourseById(courseId);
        Enrollment enrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId, LocalDate.now(), Status.ACTIVE);
        items.add(enrollment);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment enrollment : items) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }
        return result;
    }

    public void markEnrollmentStatus(int enrollmentId, Status status) throws EntityNotFoundException {
        Enrollment enrollment = findById(items, enrollmentId, "Enrollment", Enrollment::getId);
        enrollment.setStatus(status);
    }

    public List<Enrollment> listEnrollments() {
        return items;
    }
}
