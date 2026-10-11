package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final ArrayList<Student> students = new ArrayList<>();

    /** Adds a student with an email. */
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        String cleanFirst = InputValidator.requireNonBlank(firstName, "First name");
        String cleanLast = InputValidator.requireNonBlank(lastName, "Last name");
        String cleanBatch = InputValidator.requireNonBlank(batch, "Batch");
        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Email is not valid.");
        }
        Student student = new Student(IdGenerator.getNextStudentId(), cleanFirst, cleanLast,
                email.trim(), cleanBatch);
        students.add(student);
        return student;
    }

    /** Overloaded: adds a student without an email. */
    public Student addStudent(String firstName, String lastName, String batch) {
        String cleanFirst = InputValidator.requireNonBlank(firstName, "First name");
        String cleanLast = InputValidator.requireNonBlank(lastName, "Last name");
        String cleanBatch = InputValidator.requireNonBlank(batch, "Batch");
        Student student = new Student(IdGenerator.getNextStudentId(), cleanFirst, cleanLast, cleanBatch);
        students.add(student);
        return student;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        throw new EntityNotFoundException("Student", id);
    }

    /** Overloaded: finds students whose name contains the given text. */
    public List<Student> findStudents(String nameQuery) {
        String query = InputValidator.requireNonBlank(nameQuery, "Search text").toLowerCase();
        List<Student> matches = new ArrayList<>();
        for (Student student : students) {
            if (student.getFullName().toLowerCase().contains(query)) {
                matches.add(student);
            }
        }
        return matches;
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student updateStudent(int id, String firstName, String lastName, String email, String batch) {
        Student student = findStudentById(id);
        student.setFirstName(InputValidator.requireNonBlank(firstName, "First name"));
        student.setLastName(InputValidator.requireNonBlank(lastName, "Last name"));
        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Email is not valid.");
        }
        student.setEmail(email.trim());
        student.setBatch(InputValidator.requireNonBlank(batch, "Batch"));
        return student;
    }

    public void removeStudent(int id) {
        students.remove(findStudentById(id));
    }

    /** Soft delete: keeps the record but marks the student inactive. */
    public void deactivateStudent(int id) {
        findStudentById(id).setActive(false);
    }
}
