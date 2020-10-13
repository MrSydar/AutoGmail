package com.mrsydar.mailing;

import com.mrsydar.ApplicationManager;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.PrintStream;
import java.util.Properties;

public class MailManager {
    private Session session;
    private final Properties properties;

    public MailManager(String host, String port, boolean sslEnable){
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

    public void send(String from, String[] to, String title, String body, boolean isHtmlMessage, ApplicationManager appManager){
        Sender sender = new Sender(from, to, title,  body, isHtmlMessage, session, appManager);
        sender.start();
    }
}
