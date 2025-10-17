package user;

import course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private final String name;
    private final String password;
    private final List<Course> enrolled = new ArrayList<>();

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() { return name; }
    public boolean checkPassword(String pass) { return Objects.equals(password, pass); }

    public void enroll(Course c) { enrolled.add(c); }
    public List<Course> getEnrolledCourses() { return enrolled; }

    @Override
    public String toString() {
        return "Student{name='%s', courses=%d}".formatted(name, enrolled.size());
    }
}