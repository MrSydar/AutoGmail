package com.mrsydar.GUI.custom_components;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JLogger extends OutputStream {

    private final JTextArea textArea;
    private final StringBuilder sb = new StringBuilder();
    private final DateTimeFormatter dtf;
    private PrintStream logFileStream;

    public JLogger(final JTextArea textArea) {
        this.textArea = textArea;
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss ");
        connectLogFile();
        sb.append(getTime()).append("> ");
    }

    private String getTime(){
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void connectLogFile(){
        File logFile = new File("log.txt");
        try {
            if (logFile.createNewFile()) System.out.println("New log file created\n");
            else System.out.println("Log file found\n");
            this.logFileStream = new PrintStream(new FileOutputStream(logFile));
        }
        catch (IOException e) {
            System.err.println(e.toString());
        };
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }

    @Override
    public void write(int b) {
//        logFileStream.write((char) b);

        if (b == '\r')
            return;

        if (b == '\n') {
            final String text = sb.toString() + "\n";
            SwingUtilities.invokeLater(() -> textArea.append(text));

            logFileStream.println(sb);

            sb.setLength(0);
            sb.append(getTime()).append("> ");

            return;
        }
        sb.append((char) b);
    }
}