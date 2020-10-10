package com.mrsydar.GUI;

import com.mrsydar.GUI.custom_components.JImage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Application extends JFrame {

    private Container container;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JCheckBox showPassword;
    private JImage frontImage;

    public Application() {
        initVariables();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        this.setTitle("Auto Gmail");

        URL url = getClass().getResource("/images/gmail_logo_128.png");
        if(url != null) this.setIconImage(new ImageIcon(url).getImage());

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
            frontImage = new JImage("/images/gmail_logo_128.png");
        } catch (IOException ex){
            System.out.println("Image load error");
        }
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
        c.fill = GridBagConstraints.BOTH;

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        container.add(frontImage, c);

        c.gridwidth = 1;
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
