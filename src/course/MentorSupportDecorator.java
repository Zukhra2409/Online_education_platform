package course;

public class MentorSupportDecorator extends CourseDecorator {
    public MentorSupportDecorator(Course inner) { super(inner); }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println(" Personal mentor support enabled.");
    }
}