package com.mrsydar;

import com.mrsydar.GUI.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApplicationManager extends Component implements ActionListener {
    Application app;
    MailManager mailManager;
    File titleFile, bodyFile, recipientsFile;

    public ApplicationManager(Application _app){
        app = _app;
        mailManager = new MailManager( "smtp.gmail.com", "465", true, this);

        log("autogmail started");

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

    public void log(String log){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss ");
        LocalDateTime now = LocalDateTime.now();
        app.log.append(dtf.format(now) + log + "\n");
    }

    public void flushLogReport() {
        File logFile = new File("log.txt");
        try {
            logFile.createNewFile();
            FileWriter fr = new FileWriter(logFile, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(app.log.getText());
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == app.showPasswordCheckBox){
            if(app.showPasswordCheckBox.isSelected())
                app.passwordField.setEchoChar((char)0);
            else
                app.passwordField.setEchoChar('•');
        }
        else if(e.getSource() == app.getTitleButton) {
            int returnVal = app.fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                titleFile = app.fc.getSelectedFile();
                app.log.append("Opening: " + titleFile.getName() + "." + "\n");
                app.getTitleButton.setText(titleFile.getName());
            } else {
                app.log.append("Open command cancelled by user." + "\n");
            }
            app.log.setCaretPosition(app.log.getDocument().getLength());
        }
        else if(e.getSource() == app.getBodyButton) {
            int returnVal = app.fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                bodyFile = app.fc.getSelectedFile();
                app.log.append("Opening: " + bodyFile.getName() + "." + "\n");
                app.getTitleButton.setText(bodyFile.getName());
            } else {
                app.log.append("Open command cancelled by user." + "\n");
            }
            app.log.setCaretPosition(app.log.getDocument().getLength());
        }
        else if(e.getSource() == app.getRecipientsButton) {
            int returnVal = app.fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                recipientsFile = app.fc.getSelectedFile();
                app.log.append("Opening: " + recipientsFile.getName() + "." + "\n");
                app.getTitleButton.setText(recipientsFile.getName());
            } else {
                app.log.append("Open command cancelled by user." + "\n");
            }
            app.log.setCaretPosition(app.log.getDocument().getLength());
        }
    }
}
