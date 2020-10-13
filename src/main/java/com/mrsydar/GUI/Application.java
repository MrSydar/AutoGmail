package com.mrsydar.GUI;

import com.mrsydar.ApplicationManager;
import com.mrsydar.GUI.custom_components.JFlipFlopButton;
import com.mrsydar.GUI.custom_components.JImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public class Application extends JFrame {

    private Container container;

    public JTextArea log;
    public JFileChooser fc;
    private JImage frontImage;
    public JTextField userField;
    public JPasswordField passwordField;
    public JCheckBox showPasswordCheckBox;
    public JButton getTitleButton, getBodyButton, getRecipientsButton, sendButton;
    public JFlipFlopButton bodyTypeButton;
    private JLabel userLabel, passwordLabel, emailSectionLabel,titleLabel, bodyLabel, recipientsLabel, logLabel;

    public Application() {
        initVariables();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        URL url = getClass().getResource("/images/gmail_logo_128.png");
        if(url != null) this.setIconImage(new ImageIcon(url).getImage());
        this.setTitle("Auto Gmail");
        this.setBounds(10,10,370,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initVariables(){
        container = getContentPane();

        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);

        ApplicationManager appManager = new ApplicationManager(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Application closed");
            }
        });

        try {
            frontImage = new JImage("/images/gmail_logo_128.png");
        } catch (IOException ex){
            System.err.println("Image load error");
        }

        userLabel = new JLabel("LOGIN ");
        passwordLabel = new JLabel("PASSWORD ");
        emailSectionLabel = new JLabel("E-MAIL:");
        titleLabel = new JLabel("TITLE");
        bodyLabel = new JLabel("BODY");
        recipientsLabel = new JLabel("RECIPIENTS");
        logLabel = new JLabel("LOG");

        userField = new JTextField();
        userField.setMinimumSize(new Dimension(200,20));
        userField.setMaximumSize(new Dimension(200,20));
        userField.setPreferredSize(new Dimension(200,20));

        passwordField = new JPasswordField();
        passwordField.setMinimumSize(new Dimension(200,20));
        passwordField.setMaximumSize(new Dimension(200,20));
        passwordField.setPreferredSize(new Dimension(200,20));

        getTitleButton = new JButton("OPEN FILE");
        getTitleButton.setMinimumSize(new Dimension(180,30));
        getTitleButton.setMaximumSize(new Dimension(180,30));
        getTitleButton.setPreferredSize(new Dimension(180,30));
        getTitleButton.addActionListener(appManager);

        getBodyButton = new JButton("OPEN FILE");
        getBodyButton.setMinimumSize(new Dimension(115,30));
        getBodyButton.setMaximumSize(new Dimension(115,30));
        getBodyButton.setPreferredSize(new Dimension(115,30));
        getBodyButton.addActionListener(appManager);

        bodyTypeButton = new JFlipFlopButton("TEXT", "HTML");
        bodyTypeButton.setMinimumSize(new Dimension(60,30));
        bodyTypeButton.setMaximumSize(new Dimension(60,30));
        bodyTypeButton.setPreferredSize(new Dimension(60,30));

        getRecipientsButton = new JButton("OPEN FILE");
        getRecipientsButton.setMinimumSize(new Dimension(180,30));
        getRecipientsButton.setMaximumSize(new Dimension(180,30));
        getRecipientsButton.setPreferredSize(new Dimension(180,30));
        getRecipientsButton.addActionListener(appManager);

        sendButton = new JButton("SEND");
        sendButton.setMinimumSize(new Dimension(200,50));
        sendButton.setMaximumSize(new Dimension(200,50));
        sendButton.setPreferredSize(new Dimension(200,50));
        sendButton.addActionListener(appManager);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.addActionListener(appManager);
    }

    public void setLayoutManager() {
        container.setLayout(new GridBagLayout());
    }

    public void setLocationAndSize() {
        userLabel.setSize(100,30);
        passwordLabel.setSize(100,30);
        userField.setSize(150,30);
        passwordField.setSize(150,30);
        showPasswordCheckBox.setSize(150,30);
    }

    public void addComponentsToContainer(){
        GridBagConstraints c = new GridBagConstraints();
        JPanel p;

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
        p = new JPanel();
        p.add(userField);
        container.add(p, c);

        c.gridx = 1;
        c.gridy = 2;
        p = new JPanel();
        p.add(passwordField);
        container.add(p, c);

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

        c.gridx = 0;
        c.gridy = 6;
        container.add(bodyLabel, c);

        c.gridx = 0;
        c.gridy = 7;
        container.add(recipientsLabel, c);

        c.gridx = 1;
        c.gridy = 5;
        p = new JPanel();
        p.add(getTitleButton);
        container.add(p, c);

        c.gridx = 1;
        c.gridy = 6;
        p = new JPanel();
        p.add(getBodyButton);
        p.add(bodyTypeButton);
        container.add(p, c);

        c.gridx = 1;
        c.gridy = 7;
        p = new JPanel();
        p.add(getRecipientsButton);
        container.add(p, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 8;
        c.fill = GridBagConstraints.BOTH;
        container.add(sendButton, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 9;
        container.add(logLabel, c);

        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 10;
        JScrollPane logScrollPane = new JScrollPane(log);
        container.add(logScrollPane, c);
    }
}
