package com.mrsydar.GUI.custom_components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFlipFlopButton extends JButton implements ActionListener {
    private final String[] textValues;
    public boolean state;

    private void changeState(){
        state = !state;
        this.setText(state ? textValues[0] : textValues[1]);
    }

    public JFlipFlopButton (String falseText, String trueText){
        super(falseText);
        this.addActionListener(this);
        state = false;
        textValues = new String[2];
        textValues[0] = trueText;
        textValues[1] = falseText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeState();
    }
}
