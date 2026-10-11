package com.airtribe.learntrack.util;

/**
 * Generates unique IDs. Counters are static, so they are shared across the whole program.
 */
public class IdGenerator {

    private static int studentIdCounter = 0;
    private static int courseIdCounter = 0;
    private static int enrollmentIdCounter = 0;

    // Utility class: no instances needed.
    private IdGenerator() {
    }

    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }
}
