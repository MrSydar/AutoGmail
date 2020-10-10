package com.mrsydar.GUI.custom_components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JImage extends JLabel {

    public JImage(String path) throws IOException {
        super(getPicture(path));
    }

    private static ImageIcon getPicture(String path) throws IOException {
        URL url = JImage.class.getResource(path);
        BufferedImage img = ImageIO.read(new File(url.getPath()));
        return new ImageIcon(img);
    }
}
