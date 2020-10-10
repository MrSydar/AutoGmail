package com.mrsydar;

import com.mrsydar.GUI.Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationManager extends Component implements ActionListener {
    Application app;

    public ApplicationManager(Application _app){
        app = _app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == app.showPasswordCheckBox){
            if(app.showPasswordCheckBox.isSelected())
                app.passwordField.setEchoChar((char)0);
            else
                app.passwordField.setEchoChar('•');
        }
    }
}
