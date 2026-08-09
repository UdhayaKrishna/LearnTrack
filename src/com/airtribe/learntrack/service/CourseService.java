package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseService extends BaseService<Course> {
    private static final List<Course> courses = new ArrayList<>();

    public CourseService() {
        super(courses);
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) throws InvalidInputException {
        try {
            ValidationUtil.validateText(courseName, "course name");
            ValidationUtil.validateText(description, "description");
            ValidationUtil.validatePositiveInteger(durationInWeeks, "Duration");
            Course course = new Course(IdGenerator.getNextCourseId(), courseName.trim(), description.trim(), durationInWeeks, true);
            courses.add(course);
            return course;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public List<Course> listCourses() {
        return new ArrayList<>(items);
    }

    public Course findCourseById(int id) throws EntityNotFoundException {
        return findById(id, "Course", Course::getId);
    }

    public void deactivateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(false);
    }

    public void activateCourse(int id) throws EntityNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
    }
}
