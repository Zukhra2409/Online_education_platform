package course;

public abstract class CourseDecorator implements Course {
    protected final Course inner;
    protected CourseDecorator(Course inner) { this.inner = inner; }

    @Override public String getTitle() { return inner.getTitle(); }
    @Override public double getPrice() { return inner.getPrice(); }
    @Override public String getLevel() { return inner.getLevel(); }

    @Override
    public void deliverContent() { inner.deliverContent(); }
}