package course;

public class ProgrammingCourse extends BaseCourse {
    public ProgrammingCourse() {
        super("Programming", 120.0, "Intermediate");
    }

    @Override
    public void deliverContent() {
        System.out.println("💻 Programming: Java syntax, OOP, patterns.");
    }
}