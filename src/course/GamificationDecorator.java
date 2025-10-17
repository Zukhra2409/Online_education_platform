package course;

public class GamificationDecorator extends CourseDecorator {
    public GamificationDecorator(Course inner) { super(inner); }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println(" Gamification: earn points & join leaderboard!");
    }
}