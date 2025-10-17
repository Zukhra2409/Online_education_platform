package service;

public class EmailAdapter extends NotificationService {
    private final ExternalEmailAPI emailAPI;

    public EmailAdapter(ExternalEmailAPI api) {
        this.emailAPI = api;
    }

    @Override
    public void send(String message) {
        emailAPI.sendEmail(
                "student@example.com",
                "EduSmart LMS Notification",
                message
        );
    }
}