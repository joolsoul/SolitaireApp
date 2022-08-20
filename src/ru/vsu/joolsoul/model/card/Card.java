package ru.vsu.joolsoul.model.card;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Card {

    private final CardSuit suit;
    private final CardRank rank;

    private Image image;

    private boolean faceUp;

    public Card(CardSuit suit, CardRank rank, Image image) {
        this.suit = suit;
        this.rank = rank;
        this.image = image;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void showFace() {
        faceUp = true;
    }

    public Image getImage() {
        if (!faceUp) {
            try {
                return ImageIO.read(new File("resources/cardBackSide.png"));
            } catch (IOException ignored) {
            }
        }
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "(" + suit.getSuit() + rank.getRank() + ")";
    }
}
