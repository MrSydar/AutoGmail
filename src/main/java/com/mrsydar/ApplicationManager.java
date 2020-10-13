package com.mrsydar;

import com.mrsydar.GUI.Application;
import com.mrsydar.GUI.custom_components.JLogger;
import com.mrsydar.mailing.MailManager;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ApplicationManager extends Component implements ActionListener {
    private final Application app;
    private final MailManager mailManager;
    private File titleFile, bodyFile, recipientsFile;
    private PrintStream loggerPrintStream;
    private String userLogin, userPassword, mailTitle, mailBody;
    private String[] mailRecipients;

    public ApplicationManager(Application _app){
        app = _app;
        mailManager = new MailManager( "smtp.gmail.com", "465", true);

        setupLogger();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (
                IllegalAccessException |
                InstantiationException |
                UnsupportedLookAndFeelException |
                ClassNotFoundException e
        ) {
            System.err.println(e.toString());
        }
    }

    private void setupLogger(){
        loggerPrintStream = new PrintStream(new JLogger(app.log));
        System.setOut(loggerPrintStream);
        System.setErr(loggerPrintStream);
    }

    public void enableButtons(boolean enable){
        app.sendButton.setEnabled(enable);
        app.getTitleButton.setEnabled(enable);
        app.getBodyButton.setEnabled(enable);
        app.getRecipientsButton.setEnabled(enable);
        app.bodyTypeButton.setEnabled(enable);
    }

    private void showPassword(boolean show){
        if(show)
            app.passwordField.setEchoChar((char)0);
        else
            app.passwordField.setEchoChar('•');
    }

    private boolean checkInputData(){
        if(titleFile == null || bodyFile == null || recipientsFile == null){
            System.err.println("Not every file selected");
            return false;
        }

        try {
            mailTitle = FileUtils.readFileToString(titleFile, "UTF-8");
            mailBody = FileUtils.readFileToString(bodyFile, "UTF-8");
            String mailRecipients = FileUtils.readFileToString(recipientsFile, "UTF-8");
            this.mailRecipients = mailRecipients.split("\n");
            //TODO: regex on split recipients
        } catch (IOException e) {
            System.err.println(e.toString());
            return false;
        }

        userLogin = app.userField.getText().replaceAll(" ", "");
        //TODO: regex on login
        userPassword = String.valueOf(app.passwordField.getPassword()).replaceAll(" ", "");
        //TODO: regex on password

        if(userLogin.length() == 0 || userPassword.length() == 0){
            System.err.println("Bad login or password input data");
            return false;
        }

        System.out.println(mailTitle);
        return true;
    }

    private void startSending(){
        mailManager.createSession(userLogin, userPassword);
        mailManager.enableSessionDebug(loggerPrintStream);
        mailManager.send(userLogin, mailRecipients, mailTitle, mailBody, app.bodyTypeButton.state, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == app.showPasswordCheckBox){
            showPassword(app.showPasswordCheckBox.isSelected());
        }
        else {
            if (e.getSource() == app.sendButton) {
                enableButtons(false);
                System.out.println("Checking input data..");
                if(checkInputData()) {
                    System.out.println("Input data is OK..\nSending.. ");
                    startSending();
                }
                else
                    System.err.println("Sending canceled due to bad input data;");
            }
            else {
                int returnVal = app.fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (e.getSource() == app.getTitleButton) {
                        titleFile = app.fc.getSelectedFile();
                        app.getTitleButton.setText(titleFile.getName());
                        System.out.println("Selected: " + titleFile.getName() + " as title file;");
                    }
                    else if (e.getSource() == app.getBodyButton) {
                        bodyFile = app.fc.getSelectedFile();
                        app.getBodyButton.setText(bodyFile.getName());
                        System.out.println("Selected: " + bodyFile.getName() + " as body file;");
                    }
                    else {
                        recipientsFile = app.fc.getSelectedFile();
                        app.getRecipientsButton.setText(recipientsFile.getName());
                        System.out.println("Selected: " + recipientsFile.getName() + " as recipients file;");
                    }
                }
                else
                    System.out.println("User has cancelled file selection;");
            }
        }
    }
}
