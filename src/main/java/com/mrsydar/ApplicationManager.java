package com.mrsydar;

import com.mrsydar.GUI.Application;
import com.mrsydar.GUI.custom_components.JLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationManager extends Component implements ActionListener {
    Application app;
    MailManager mailManager;
    File titleFile, bodyFile, recipientsFile;
    JCheckBox showPasswordCheckBox;
    PrintStream loggerPrintStream;

    public ApplicationManager(Application _app){
        app = _app;
        mailManager = new MailManager( "smtp.gmail.com", "465", true, this);

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

    private void enableButtons(boolean enable){
        app.sendButton.setEnabled(enable);
        app.getTitleButton.setEnabled(enable);
        app.getBodyButton.setEnabled(enable);
        app.getRecipientsButton.setEnabled(enable);
    }

    private void showPassword(boolean show){
        if(show)
            app.passwordField.setEchoChar((char)0);
        else
            app.passwordField.setEchoChar('•');
    }

    private boolean checkInputData(){
        System.out.println("Not implemented yet");
        return true;
    }

    private void startSending(){
//        mailManager.createSession(login, password);
//        mailManager.enableSessionDebug(loggerPrintStream);
        System.out.println("Not implemented yet");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == showPasswordCheckBox){
            showPassword(showPasswordCheckBox.isSelected());
        }
        else {
            if (e.getSource() == app.sendButton) {
                enableButtons(false);
                System.out.println("Checking input data..");
                if(checkInputData()) {
                    System.out.println("Input data is OK..\nSending.. ");
                    startSending();
                    System.out.println("Done");
                }
                else
                    System.err.println("Sending canceled due to bad input data;");
                enableButtons(true);
            }
            else {
                int returnVal = app.fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (e.getSource() == app.getTitleButton) {
                        titleFile = app.fc.getSelectedFile();
                        app.getTitleButton.setText(bodyFile.getName());
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
