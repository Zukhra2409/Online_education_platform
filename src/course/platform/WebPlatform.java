package course.platform;

public class WebPlatform implements LearningPlatform {
    @Override
    public void renderCourse(String title) {
        System.out.println("Course \"" + title + "\" launched on Web platform.");
    }
}