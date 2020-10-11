package com.mrsydar;

import com.mrsydar.GUI.custom_components.JLogger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintStream;
import java.util.Properties;

class MailManager {
    private Session session;
    private final Properties properties;

    public MailManager(String host, String port, boolean sslEnable, ApplicationManager _appManager){
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.enable", sslEnable ? "true" : "false");
        properties.put("mail.smtp.auth", "true");
    }

    public void enableSessionDebug(PrintStream ps){
        session.setDebug(true);
        session.setDebugOut(ps);
    }

    public void createSession(String login, String password){
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });
    }

    public void send(String from, String to, String title, String body, boolean isHtmlMessage){
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(title);

            message.setText(
                    body,
                    "utf-8",
                    "text/" + ( isHtmlMessage ? "html" : "plain" ) + "; charset=UTF-8"
            );

            Transport.send(message);
        } catch (MessagingException mex) {
            System.err.println(mex.toString());
        }
    }
}
