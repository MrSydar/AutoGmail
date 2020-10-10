package com.mrsydar.GUI.custom_components;

import javax.swing.*;
import java.io.OutputStream;

public class JLogger extends OutputStream {

    private final JTextArea textArea;
    private final StringBuilder sb = new StringBuilder();
    private final String title;

    public JLogger(final JTextArea textArea, String title) {
        this.textArea = textArea;
        this.title = title;
        sb.append(title).append("> ");
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public void write(int b) {

        if (b == '\r')
            return;

        if (b == '\n') {
            final String text = sb.toString() + "\n";
            SwingUtilities.invokeLater(() -> textArea.append(text));
            sb.setLength(0);
            sb.append(title).append("> ");

            return;
        }

        sb.append((char) b);
    }
}