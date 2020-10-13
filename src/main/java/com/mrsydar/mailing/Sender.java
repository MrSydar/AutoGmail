package com.mrsydar.mailing;

import com.mrsydar.ApplicationManager;
import com.mrsydar.GUI.Application;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender extends Thread {
    private final String from, title, body;
    private final String[] to;
    private final boolean isHtmlMessage;
    private final Session session;
    private final ApplicationManager appManager;

    public Sender(String from, String[] to, String title, String body, boolean isHtmlMessage, Session session, ApplicationManager appManager){
        this.from = from;
        this.to = to;
        this.title = title;
        this.body = body;
        this.isHtmlMessage = isHtmlMessage;
        this.session = session;
        this.appManager = appManager;
    }

    @Override
    public void run(){
        for(String recipient : to) {
            try {
                System.out.println("Sending to: " + recipient);

                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress(from));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                message.setSubject(title);

                message.setText(
                        body,
                        "utf-8",
                        (isHtmlMessage ? "html" : "plain")
                );

                Transport.send(message);
            } catch (MessagingException mex) {
                System.err.println(mex.toString());
            }
            System.out.println("Done");
            appManager.enableButtons(true);
        }
    }
}
