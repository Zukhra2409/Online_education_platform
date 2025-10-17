package course;

public class CourseBuilder {
    private String title;
    private double price;
    private String level;
    private String type;

    public CourseBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public CourseBuilder setLevel(String level) {
        this.level = level;
        return this;
    }

    public CourseBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public Course build() {
        switch (type.toLowerCase()) {
            case "math" -> { return new MathCourse(); }
            case "programming" -> { return new ProgrammingCourse(); }
            case "art" -> { return new ArtCourse(); }
            default -> {
                return new BaseCourse(title, price, level) {
                    @Override
                    public void deliverContent() {
                        System.out.println("📚 Delivering custom course content...");
                    }
                };
            }
        }
    }
}