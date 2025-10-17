package course.platform;

public class MobilePlatform implements LearningPlatform {
    @Override
    public void renderCourse(String title) {
        System.out.println(" Course \"" + title + "\" launched on Mobile platform.");
    }
}