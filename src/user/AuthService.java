package user;

import app.Database;

public class AuthService {
    private final Database db;

    public AuthService(Database db) { this.db = db; }

    public boolean register(String name, String password) {
        if (db.containsStudentName(name)) return false;
        db.addStudent(new Student(name, password));
        return true;
    }

    public Student login(String name, String password) {
        return db.getStudents().stream()
                .filter(s -> s.getName().equalsIgnoreCase(name) && s.checkPassword(password))
                .findFirst()
                .orElse(null);
    }
}