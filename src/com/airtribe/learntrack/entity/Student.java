package com.airtribe.learntrack.entity;

/**
 * A learner. Extends Person and adds batch and active status.
 */
public class Student extends Person {

    private String batch;
    private boolean active;

    public Student() {
        super();
        this.active = true;
    }

    /** Constructor with email. */
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    /** Overloaded constructor without email. */
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "not provided", batch);
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return getFullName() + " [Batch " + batch + "]";
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name=%s, email=%s, batch=%s, active=%s}",
                getId(), getFullName(), getEmail(), batch, active);
    }
}
