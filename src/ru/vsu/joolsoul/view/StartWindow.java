package ru.vsu.joolsoul.view;

import javax.swing.*;

public class StartWindow extends JFrame {

    private final int WIDTH = 400;
    private final int HEIGHT = 350;

    public StartWindow() {
        setTitle("Start window");
        setSize(WIDTH, HEIGHT);
        ImageIcon icon = new ImageIcon("resources/startImage.png");
        setIconImage(icon.getImage());
        add(new StartPanel());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
