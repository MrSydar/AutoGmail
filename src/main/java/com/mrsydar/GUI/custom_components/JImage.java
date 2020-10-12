package com.mrsydar.GUI.custom_components;

import com.mrsydar.GUI.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JImage extends JLabel {

    public JImage(String path) throws IOException {
        super(getPicture(path));
    }

    private static ImageIcon getPicture(String path) throws IOException {
        InputStream is = Application.class.getResourceAsStream(path);
        BufferedImage img = ImageIO.read(is);
        return new ImageIcon(img);
    }
}
