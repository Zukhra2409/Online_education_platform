package service;

import course.Course;
import user.Student;

import java.util.HashMap;
import java.util.Map;

public class ProgressTracker {
    private final Map<Student, Map<Course, Integer>> progress = new HashMap<>();

    public void addCourse(Student s, Course c) {
        progress.computeIfAbsent(s, k -> new HashMap<>()).putIfAbsent(c, 0);
    }

    public void completeLesson(Student s, Course c) {
        progress.computeIfAbsent(s, k -> new HashMap<>()).putIfAbsent(c, 0);
        int val = progress.get(s).get(c);
        if (val < 100) {
            val = Math.min(100, val + 10);
            progress.get(s).put(c, val);
            System.out.println("✅ +" + 10 + "% progress in " + c.getTitle());
        } else {
            System.out.println("✔ Course already completed.");
        }
    }

    public void showProgress(Student s) {
        System.out.println("\n📊 " + s.getName() + " — Progress");
        Map<Course, Integer> map = progress.get(s);
        if (map == null || map.isEmpty()) {
            System.out.println("No enrolled courses yet.");
            return;
        }
        map.forEach((c, p) -> System.out.printf(" - %s: %d%%%n", c.getTitle(), p));
    }
}