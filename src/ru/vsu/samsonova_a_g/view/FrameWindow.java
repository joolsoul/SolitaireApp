package ru.vsu.samsonova_a_g.view;

import ru.vsu.samsonova_a_g.model.Player;

import javax.swing.*;

public class FrameWindow extends JFrame {

    private final int WIDTH = 1000;
    private final int HEIGHT = 1100;

    public FrameWindow(Player player) {
        setTitle("Solitaire");
        setSize(WIDTH, HEIGHT);
        ImageIcon icon = new ImageIcon("resources/appImage.jpg");
        setIconImage(icon.getImage());
        add(new MainPanel(player));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
