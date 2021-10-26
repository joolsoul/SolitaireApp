package ru.vsu.samsonova_a_g.model.card;

import ru.vsu.samsonova_a_g.model.ColorType;

public enum CardSuit {
    HEARTS('♥', ColorType.RED),
    CLUBS('♣', ColorType.BLACK),
    SPADES('♠', ColorType.BLACK),
    DIAMONDS('♦', ColorType.RED);

    private char suit;
    private ColorType colorType;

    CardSuit(char suit, ColorType colorType) {
        this.suit = suit;
        this.colorType = colorType;
    }

    public char getSuit() {
        return suit;
    }

    public ColorType getColorType() {
        return colorType;
    }
}
