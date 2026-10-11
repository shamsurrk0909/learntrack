# Design Notes

## Why ArrayList instead of an array?

The number of students, courses and enrollments changes while the program runs. A plain array has a fixed size, so adding past its length means creating a bigger array and copying everything over by hand, and removing an element leaves a gap to shift. `ArrayList` grows and shrinks automatically and gives `add`, `remove`, `size` and `get` for free, so the service code stays short and readable. Arrays would still be a fine choice if the size were known and fixed in advance.

## Where static members are used and why

- **`IdGenerator`**: `studentIdCounter`, `courseIdCounter` and `enrollmentIdCounter` are `private static` fields, and `getNextStudentId()`, `getNextCourseId()` and `getNextEnrollmentId()` are `static` methods. IDs must be unique across the whole program, not per object, so the counter belongs to the class itself. The constructor is private because the class is never instantiated.
- **`InputValidator`**: static helper methods (`requireNonBlank`, `parseInt`, `isValidEmail`) hold no state, so there is no reason to create an object to call them.
- **`Main`**: the `Scanner` and the three services are `static` fields because `main` is a static method and there is only one of each in this app.

## Where inheritance is used and what it gives us

`Person` holds the shared fields (`id`, `firstName`, `lastName`, `email`) and behaviour (`getFullName()`, `getDisplayName()`). `Student` and `Trainer` extend it.

- **Less duplication:** the common fields and getters/setters are written once.
- **`super` in constructors:** `Student` and `Trainer` call `super(...)` to initialise the inherited fields.
- **Overriding / polymorphism:** `getDisplayName()` is overridden. `Student` appends its batch, `Trainer` prefixes the title and expertise. Code holding a `Person` reference gets the right behaviour without checking the actual type.
- **Easy to extend:** a new kind of person (for example an Admin) only needs to add what is different.

## Other design choices

- **Constructor overloading:** `Student` has constructors with and without email; `Enrollment` has one that defaults the date to today.
- **Method overloading:** `StudentService.addStudent(...)` has a version with and without email, and `findStudentById(int)` / `findStudents(String)` search in different ways.
- **Separation of concerns:** entities only hold data, services hold the rules (for example no duplicate active enrollment, no enrolling inactive students), and `Main` only shows menus, reads input and calls services.
- **Soft delete:** students and courses are deactivated instead of removed, so past enrollments stay meaningful. `removeStudent` exists on the service for completeness.
- **Custom exceptions:** `EntityNotFoundException` and `InvalidInputException` are caught in `Main`, which prints a short message and returns to the menu.
- **Enum for status:** `EnrollmentStatus` (ACTIVE, COMPLETED, CANCELLED) prevents typos that a plain `String` would allow.
