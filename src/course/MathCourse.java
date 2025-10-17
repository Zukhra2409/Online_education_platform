package course;

public class MathCourse extends BaseCourse {
    public MathCourse() {
        super("Mathematics", 100.0, "Beginner");
    }

    @Override
    public void deliverContent() {
        System.out.println("📘 Math: Algebra → Geometry → Calculus.");
    }
}