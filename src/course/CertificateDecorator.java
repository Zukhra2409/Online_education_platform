package course;

public class CertificateDecorator extends CourseDecorator {
    public CertificateDecorator(Course inner) { super(inner); }
    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("🎓 Certificate will be granted after completion!");
    }
}