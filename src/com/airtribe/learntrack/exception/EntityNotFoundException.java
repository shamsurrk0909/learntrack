package com.airtribe.learntrack.exception;

/** Thrown when a Student, Course or Enrollment with the given ID does not exist. */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, int id) {
        super(entityName + " with ID " + id + " was not found.");
    }
}
