package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

/**
 * Console UI. Only displays menus, reads input and calls the service classes.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = readLine("Choose an option: ");
            if (choice == null) {
                break; // end of input
            }
            try {
                switch (choice.trim()) {
                    case "1":
                        studentMenu();
                        break;
                    case "2":
                        courseMenu();
                        break;
                    case "3":
                        enrollmentMenu();
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 0-3.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    // ---------- Menus ----------

    private static void printMainMenu() {
        System.out.println();
        System.out.println("===== LearnTrack =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--- Student Management ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate student");
            System.out.println("0. Back");
            String choice = readLine("Choose an option: ");
            if (choice == null) {
                return;
            }
            try {
                switch (choice.trim()) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        viewStudents();
                        break;
                    case "3":
                        searchStudent();
                        break;
                    case "4":
                        deactivateStudent();
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 0-4.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--- Course Management ---");
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate course");
            System.out.println("4. Deactivate course");
            System.out.println("0. Back");
            String choice = readLine("Choose an option: ");
            if (choice == null) {
                return;
            }
            try {
                switch (choice.trim()) {
                    case "1":
                        addCourse();
                        break;
                    case "2":
                        viewCourses();
                        break;
                    case "3":
                        courseService.activateCourse(readInt("Course ID: "));
                        System.out.println("Course activated.");
                        break;
                    case "4":
                        courseService.deactivateCourse(readInt("Course ID: "));
                        System.out.println("Course deactivated.");
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 0-4.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("--- Enrollment Management ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment completed");
            System.out.println("4. Mark enrollment cancelled");
            System.out.println("0. Back");
            String choice = readLine("Choose an option: ");
            if (choice == null) {
                return;
            }
            try {
                switch (choice.trim()) {
                    case "1":
                        enroll();
                        break;
                    case "2":
                        viewEnrollments();
                        break;
                    case "3":
                        enrollmentService.markCompleted(readInt("Enrollment ID: "));
                        System.out.println("Enrollment marked COMPLETED.");
                        break;
                    case "4":
                        enrollmentService.markCancelled(readInt("Enrollment ID: "));
                        System.out.println("Enrollment marked CANCELLED.");
                        break;
                    case "0":
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 0-4.");
                }
            } catch (EntityNotFoundException | InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------- Student actions ----------

    private static void addStudent() {
        String firstName = readLine("First name: ");
        String lastName = readLine("Last name: ");
        String email = readLine("Email (leave blank to skip): ");
        String batch = readLine("Batch: ");

        Student student;
        if (email == null || email.trim().isEmpty()) {
            student = studentService.addStudent(firstName, lastName, batch);
        } else {
            student = studentService.addStudent(firstName, lastName, email, batch);
        }
        System.out.println("Added: " + student);
    }

    private static void viewStudents() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {
        int id = readInt("Student ID: ");
        Student student = studentService.findStudentById(id);
        System.out.println(student);
        System.out.println("Display name: " + student.getDisplayName());
    }

    private static void deactivateStudent() {
        studentService.deactivateStudent(readInt("Student ID: "));
        System.out.println("Student deactivated.");
    }

    // ---------- Course actions ----------

    private static void addCourse() {
        String name = readLine("Course name: ");
        String description = readLine("Description: ");
        int weeks = readInt("Duration in weeks: ");
        Course course = courseService.addCourse(name, description, weeks);
        System.out.println("Added: " + course);
    }

    private static void viewCourses() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    // ---------- Enrollment actions ----------

    private static void enroll() {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrolled: " + enrollment);
    }

    private static void viewEnrollments() {
        int studentId = readInt("Student ID: ");
        List<Enrollment> list = enrollmentService.getEnrollmentsForStudent(studentId);
        if (list.isEmpty()) {
            System.out.println("This student has no enrollments.");
            return;
        }
        for (Enrollment enrollment : list) {
            System.out.println(enrollment);
        }
    }

    // ---------- Input helpers ----------

    private static String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        String text = readLine(prompt);
        if (text == null) {
            throw new InvalidInputException("No input received.");
        }
        return InputValidator.parseInt(text, "Value");
    }
}
