package org.dlpk;

public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice();
        invoice.amount = 100.0;
        invoice.clientEmail = "abc@gmail.com";
        invoice.clientName = "Daniel Lipkin";
        invoice.type = Invoice.Tipo.SIMPLES;
        invoice.process();

        NotificationService notificationService = new NotificationService();
        String notif_msg = "Hello World!";
        notificationService.notifyUser( new NotificationService.EmailNotification(), notif_msg);
        notificationService.notifyUser( new NotificationService.SMSNotification(), notif_msg);
        notificationService.notifyUser( new NotificationService.PushNotification(), notif_msg);

        new Document.Processor(Document.TYPE.HTML).print();
        new Document.Processor(Document.TYPE.PDF).print();
        new Document.Processor(Document.TYPE.UNKNOWN).print();
    }
}