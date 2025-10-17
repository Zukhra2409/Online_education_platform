package course;

import course.platform.LearningPlatform;

public abstract class BaseCourse implements Course {
    protected final String title;
    protected final double price;
    protected final String level;
    protected LearningPlatform platform;

    protected BaseCourse(String title, double price, String level) {
        this.title = title;
        this.price = price;
        this.level = level;
    }

    public void setPlatform(LearningPlatform platform) {
        this.platform = platform;
    }

    @Override
    public void deliverContent() {
        if (platform != null)
            platform.renderCourse(title);
        else
            System.out.println("🖥 Course " + title + " started (default platform).");
    }

    @Override public String getTitle() { return title; }
    @Override public double getPrice() { return price; }
    @Override public String getLevel() { return level; }
}