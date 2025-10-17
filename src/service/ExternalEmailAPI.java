package service;


public class ExternalEmailAPI {
    public void sendEmail(String address, String subject, String body) {
        System.out.println("📧 [External API] Email sent to " + address);
        System.out.println("    Subject: " + subject);
        System.out.println("    Body: " + body);
    }
}