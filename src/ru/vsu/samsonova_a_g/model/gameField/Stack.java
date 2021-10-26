package ru.vsu.samsonova_a_g.model.gameField;

import ru.vsu.samsonova_a_g.model.card.Card;

import java.util.LinkedList;
import java.util.List;

public class Stack {

    private List<Card> openCards = new LinkedList<>();
    private List<Card> closeCards = new LinkedList<>();

    public Stack() {

    }

    public List<Card> getOpenCards() {
        return openCards;
    }

    public void setOpenCards(List<Card> openCards) {
        this.openCards = openCards;
    }

    public List<Card> getCloseCards() {
        return closeCards;
    }

    public void setCloseCards(List<Card> closeCards) {
        this.closeCards = closeCards;
    }
}
