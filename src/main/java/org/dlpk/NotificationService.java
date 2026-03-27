package org.dlpk;

public class NotificationService {

    private interface INotification {
        void send(String message);
    }

    public static class EmailNotification implements INotification {
        public void send(String message) {
            System.out.println("Sending EMAIL: " + message);
        }
    }

    public static class SMSNotification implements INotification {
        public void send(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    public static class PushNotification implements INotification {
        public void send(String message) {
            System.out.println("Sending PUSH: " + message);
        }
    }

    public void notifyUser(INotification channel, String message) {
        channel.send(message);
    }
}