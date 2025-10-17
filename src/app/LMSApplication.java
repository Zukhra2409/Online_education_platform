package app;

import course.*;
import course.BaseCourse;
import course.platform.MobilePlatform;
import course.platform.WebPlatform;
import service.*;
import user.AuthService;
import user.Student;

import java.util.List;
import java.util.Scanner;

public class LMSApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final Database db = Database.getInstance();
    private final NotificationService notify = new EmailAdapter(new ExternalEmailAPI());

    private final AuthService auth = new AuthService(db);
    private final ProgressTracker progress = new ProgressTracker();

    private Student currentUser;

    public void start() {
        System.out.println("===== Welcome to Zukhra Edu LMS =====");
        while (true) {
            System.out.println("\n1) Register  2) Login  3) Exit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> doRegister();
                case "2" -> doLogin();
                case "3" -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void doRegister() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Create password: ");
        String pass = scanner.nextLine();
        if (auth.register(name, pass)) {
            notify.send("✅ Registration successful for " + name);
        } else {
            System.out.println("❌ User with this name already exists.");
        }
    }

    private void doLogin() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();
        Student s = auth.login(name, pass);
        if (s != null) {
            currentUser = s;
            notify.send("👋 Welcome back, " + currentUser.getName());
            mainMenu();
        } else {
            System.out.println("❌ Invalid credentials.");
        }
    }

    private void mainMenu() {
        while (currentUser != null) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1) View courses & enroll");
            System.out.println("2) My progress");
            System.out.println("3) Logout");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> showCatalogAndEnroll();
                case "2" -> progress.showProgress(currentUser);
                case "3" -> currentUser = null;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void showCatalogAndEnroll() {
        List<Course> catalog = db.getCourses();
        System.out.println("\nAvailable courses:");
        for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            System.out.printf("%d) %s | Level: %s | $%.2f%n",
                    i + 1, c.getTitle(), c.getLevel(), c.getPrice());
        }
        System.out.print("Select course (number) or 0 to cancel: ");
        String raw = scanner.nextLine().trim();
        int idx;
        try { idx = Integer.parseInt(raw); } catch (NumberFormatException e) { return; }
        if (idx <= 0 || idx > catalog.size()) return;

        Course chosen = catalog.get(idx - 1);
        chosen = decorateCourseInteractive(chosen); // Decorator

        currentUser.enroll(chosen);
        progress.addCourse(currentUser, chosen);
        notify.send("🎓 Enrolled in " + chosen.getTitle());
        learnLoop(chosen);
    }

    private Course decorateCourseInteractive(Course base) {
        Course result = base;
        System.out.print("Add Certificate? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            result = new CertificateDecorator(result);
        }
        System.out.print("Add Mentor Support? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            result = new MentorSupportDecorator(result);
        }
        System.out.print("Enable Gamification? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            result = new GamificationDecorator(result);
        }
        System.out.printf("Selected: %s (decorated)%n", result.getTitle());
        return result;
    }

    private void learnLoop(Course course) {
        while (true) {
            System.out.println("\n=== " + course.getTitle() + " ===");
            System.out.println("1) Start lesson");
            System.out.println("2) Complete lesson (+10%)");
            System.out.println("3) Back to main menu");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();
            switch (ch) {
                case "1" -> {
                    System.out.print("Choose platform (1-Web / 2-Mobile): ");
                    String p = scanner.nextLine().trim();
                    if (course instanceof BaseCourse base) {
                        base.setPlatform(p.equals("2") ? new MobilePlatform() : new WebPlatform());
                    }
                    course.deliverContent();
                }
                case "2" -> {
                    progress.completeLesson(currentUser, course);
                    progress.showProgress(currentUser);
                }
                case "3" -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}