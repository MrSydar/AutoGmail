package com.mrsydar.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {

    private Container container;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;

    public Application() {
        initVariables();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        this.setTitle("Auto Gmail");
        this.setBounds(10,10,370,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initVariables(){
        container = getContentPane();
        userLabel = new JLabel("LOGIN ");
        passwordLabel = new JLabel("PASSWORD ");
        userTextField = new JTextField();
        passwordField = new JPasswordField();
        showPassword = new JCheckBox("Show Password");

        try {

        }
        catch (IOException e){
            System.out.println("Logo load error");
        };
    }

    public void setLayoutManager() {
        container.setLayout(new GridBagLayout());
    }

    public void setLocationAndSize() {
        userLabel.setSize(100,30);
        passwordLabel.setSize(100,30);
        userTextField.setSize(150,30);
        passwordField.setSize(150,30);
        showPassword.setSize(150,30);
    }

    public void addComponentsToContainer(){
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        container.add(userLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        container.add(passwordLabel, c);

        c.gridx = 1;
        c.gridy = 1;
        container.add(userTextField, c);

        c.gridx = 1;
        c.gridy = 2;
        container.add(passwordField, c);

        c.gridx = 1;
        c.gridy = 3;
        container.add(showPassword, c);
    }
}
