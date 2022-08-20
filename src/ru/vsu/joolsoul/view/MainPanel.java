package ru.vsu.joolsoul.view;

import ru.vsu.joolsoul.model.*;
import ru.vsu.joolsoul.model.card.CardSuit;
import ru.vsu.joolsoul.model.gameField.Foundation;
import ru.vsu.joolsoul.model.gameField.Stack;
import ru.vsu.joolsoul.model.gameField.StackType;
import ru.vsu.joolsoul.service.GameService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MainPanel extends JPanel implements MouseListener {

    private final GameService gameService = new GameService();
    private final Game game;

    private final JTextField deckSize = new JTextField();
    private final JButton restartButton = new JButton("Restart");
    private final JButton exitButton = new JButton("Exit");

    public MainPanel(Player player) {
        this.game = gameService.createGame(player);

        setLayout(null);
        addMouseListener(this);
        addRestartButtonListener();
        addExitButtonListener();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        try {
            Image background = ImageIO.read(new File("resources/panel.png"));
            g.drawImage(background, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        paintStacksCards(gr, game);

        paintDeck(game, gr);
        paintOpenCards(game, gr);
        paintDeckSize(game);
        paintFoundations(game, gr);

        if (!game.isInGame()) {
            drawGameOver(gr);
            restartButton.setBackground(new Color(0x286D42));
            restartButton.setBounds((int) (this.getWidth() / 2.4), (int) (this.getHeight() / 1.7), 145, 60);
            add(restartButton);
            exitButton.setBackground(new Color(0x286D42));
            exitButton.setBounds((int) (this.getWidth() / 2.4), (int) (this.getHeight() / 1.5), 145, 60);
            add(exitButton);
        }
    }

    private void paintDeck(Game game, Graphics2D gr) {
        if (game.getGameDeck().size() > 0) {
            try {
                Image image = ImageIO.read(new File("resources/cardBackSide.png"));
                gr.drawImage(image, 35, 20, 109, 145, null);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void paintFoundations(Game game, Graphics2D gr) {
        int x = 423;
        int y = 20;

        for (Map.Entry<CardSuit, Foundation> entry : game.getFoundationsMap().entrySet()) {
            if (!entry.getValue().getFoundationCards().isEmpty()) {
                Image image = entry.getValue().getFoundationCards().get(entry.getValue().getFoundationCards().size() - 1).getImage();
                gr.drawImage(image, x, y, 109, 145, null);
            }
            x += 130;
        }
    }


    private void paintOpenCards(Game game, Graphics2D gr) {
        if (!game.getOpenCards().isEmpty()) {
            Image image = game.getOpenCards().get(game.getOpenCards().size() - 1).getImage();
            gr.drawImage(image, 164, 20, 109, 145, null);
        }
    }

    private void paintDeckSize(Game game) {
        deckSize.setText(null);
        deckSize.setEditable(false);
        if (game.getGameDeck().size() > 0) {
            deckSize.setText(String.valueOf(game.getGameDeck().size()));
        } else {
            deckSize.setText(String.valueOf(0));
        }
        deckSize.setBounds(10, 77, 20, 20);
        deckSize.setHorizontalAlignment(JTextField.CENTER);
        add(deckSize);
    }

    private void paintStacksCards(Graphics2D gr, Game game) {
        int x = 35;
        for (int i = 0; i < 7; i++, x += 129) {
            int y = 200;

            Stack currentStack = game.getStacksMap().get(StackType.values()[i]);

            for (int j = 0; j < currentStack.getCloseCards().size(); j++, y += 30) {
                Image card = currentStack.getCloseCards().get(j).getImage();

                gr.drawImage(card, x, y, 109, 145, null);
            }
            for (int j = 0; j < currentStack.getOpenCards().size(); j++, y += 30) {
                Image card = currentStack.getOpenCards().get(j).getImage();

                gr.drawImage(card, x, y, 109, 145, null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean doStep = gameService.playGame(game);
        if (!doStep) {
            game.setInGame(false);
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    private void addRestartButtonListener() {
        restartButton.addActionListener(e ->
        {
            new StartWindow();
            SwingUtilities.windowForComponent(this).dispose();
        });
    }

    private void addExitButtonListener() {
        exitButton.addActionListener(e -> SwingUtilities.windowForComponent(this).dispose());
    }

    private void drawGameOver(Graphics2D gr) {
        drawWithFont(gr, new Font("Serif", Font.BOLD, 50), new Color(0x0A3A16), () ->
        {
            gr.drawString("GAME OVER", this.getWidth() / 3, this.getHeight() / 2);
        });
        drawWithFont(gr, new Font("Serif", Font.BOLD, 30), new Color(0x0A3A16), () ->
        {
            gr.drawString("NO STEPS", (int) (this.getWidth() / 2.4), (int) (this.getHeight() / 1.8));
        });
    }

    private void drawWithFont(Graphics2D g, Font font, Color color, Runnable drawAction) {
        Font oldF = g.getFont();
        Color oldC = g.getColor();
        g.setFont(font);
        g.setColor(color);
        drawAction.run();
        g.setFont(oldF);
        g.setColor(oldC);
    }
}
