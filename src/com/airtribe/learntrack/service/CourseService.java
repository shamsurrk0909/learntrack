package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final ArrayList<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        String cleanName = InputValidator.requireNonBlank(courseName, "Course name");
        String cleanDescription = InputValidator.requireNonBlank(description, "Description");
        InputValidator.requirePositive(durationInWeeks, "Duration");
        Course course = new Course(IdGenerator.getNextCourseId(), cleanName, cleanDescription, durationInWeeks);
        courses.add(course);
        return course;
    }

    public Course findCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        throw new EntityNotFoundException("Course", id);
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    public void activateCourse(int id) {
        findCourseById(id).setActive(true);
    }

    public void deactivateCourse(int id) {
        findCourseById(id).setActive(false);
    }
}
