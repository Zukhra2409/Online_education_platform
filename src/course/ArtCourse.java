package course;

public class ArtCourse extends BaseCourse {
    public ArtCourse() {
        super("Art & Design", 80.0, "Beginner");
    }

    @Override
    public void deliverContent() {
        System.out.println("🎨 Art: color theory, composition, practice.");
    }
}