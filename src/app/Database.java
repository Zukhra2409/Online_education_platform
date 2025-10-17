package app;

import course.*;
import user.Student;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;

    private final List<Student> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();

    private Database() {
        Course math = new CourseBuilder()
                .setType("math")
                .setTitle("Mathematics")
                .setPrice(100.0)
                .setLevel("Beginner")
                .build();

        Course prog = new CourseBuilder()
                .setType("programming")
                .setTitle("Programming")
                .setPrice(120.0)
                .setLevel("Intermediate")
                .build();

        Course art = new CourseBuilder()
                .setType("art")
                .setTitle("Art & Design")
                .setPrice(80.0)
                .setLevel("Beginner")
                .build();

        courses.add(math);
        courses.add(prog);
        courses.add(art);
    }

    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    public List<Student> getStudents() { return students; }
    public List<Course> getCourses() { return courses; }

    public void addStudent(Student s) { students.add(s); }
    public boolean containsStudentName(String name) {
        return students.stream().anyMatch(st -> st.getName().equalsIgnoreCase(name));
    }
}