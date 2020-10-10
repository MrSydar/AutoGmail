package com.mrsydar.GUI;

import com.mrsydar.ApplicationManager;
import com.mrsydar.GUI.custom_components.JImage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Application extends JFrame {

    private Container container;

    private JImage frontImage;

    public JTextField userTextField;
    public JPasswordField passwordField;
    public JCheckBox showPasswordCheckBox;

    private JLabel userLabel, passwordLabel, emailSectionLabel,titleLabel, bodyLabel, recipientsLabel, logLabel;
    public JButton getTitleButton, getBodyButton, getRecipientsButton;

    private ApplicationManager appManager = new ApplicationManager(this);

    private JFileChooser fc;

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
        try {
            frontImage = new JImage("/images/gmail_logo_128.png");
        } catch (IOException ex){
            System.out.println("Image load error");
        }

        container = getContentPane();

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        userLabel = new JLabel("LOGIN ");
        passwordLabel = new JLabel("PASSWORD ");
        emailSectionLabel = new JLabel("E-MAIL:");
        titleLabel = new JLabel("TITLE");
        bodyLabel = new JLabel("BODY");
        recipientsLabel = new JLabel("RECIPIENTS");
        logLabel = new JLabel("LOG");

        userTextField = new JTextField();
        passwordField = new JPasswordField();

        getTitleButton = new JButton("OPEN");
        getTitleButton.addActionListener(e -> fc.showOpenDialog(appManager));

        getBodyButton = new JButton("OPEN");
        getBodyButton.addActionListener(e -> fc.showOpenDialog(appManager));

        getRecipientsButton = new JButton("OPEN");
        getRecipientsButton.addActionListener(e -> fc.showOpenDialog(appManager));

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(appManager);
    }

    public void setLayoutManager() {
        container.setLayout(new GridBagLayout());
    }

    public void setLocationAndSize() {
        userLabel.setSize(100,30);
        passwordLabel.setSize(100,30);
        userTextField.setSize(150,30);
        passwordField.setSize(150,30);
        showPasswordCheckBox.setSize(150,30);
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
        container.add(showPasswordCheckBox, c);

        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 4;
        container.add(emailSectionLabel, c);

        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 5;
        container.add(titleLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        JPanel p = new JPanel();
        p.add(getTitleButton);
        container.add(p, c);

        c.gridx = 0;
        c.gridy = 6;
        container.add(bodyLabel, c);

        c.gridx = 1;
        c.gridy = 6;
        JPanel p1 = new JPanel();
        p1.add(getBodyButton);
        container.add(p1, c);

        c.gridx = 0;
        c.gridy = 7;
        container.add(recipientsLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        JPanel p3 = new JPanel();
        p3.add(getRecipientsButton);
        container.add(p3, c);

        c.gridx = 0;
        c.gridy = 8;
        container.add(logLabel, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 9;
        JTextArea log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        container.add(logScrollPane, c);
    }
}
