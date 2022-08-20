package ru.vsu.joolsoul.view;

import ru.vsu.joolsoul.model.Player;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    private final JButton startButton = new JButton("Start");
    private final JTextField playerNameTextField = new JTextField();
    private final JLabel playerNameLabel = new JLabel("Enter player name:");

    public StartPanel() {
        addButtonListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        setBackground(new Color(0xADDFEA));

        startButton.setBounds((int) (this.getWidth() / 3.2), (int) (this.getHeight() / 1.9), 145, 60);
        add(startButton);

        playerNameLabel.setBounds((int) (this.getWidth() / 8.5), (int) (this.getHeight() / 3.9), 150, 40);
        add(playerNameLabel);

        playerNameTextField.setBounds((int) (this.getWidth() / 2.2), (int) (this.getHeight() / 3.9), 170, 40);
        add(playerNameTextField);
    }

    private void addButtonListener() {
        startButton.addActionListener(e ->
        {
            String name = "";
            if (playerNameTextField.getText() != null) {
                name = playerNameTextField.getText();
            }
            FrameWindow frameWindow = new FrameWindow(new Player(name));
            SwingUtilities.windowForComponent(this).dispose();
        });
    }

}
