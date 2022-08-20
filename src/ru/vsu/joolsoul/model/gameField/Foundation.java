package ru.vsu.joolsoul.model.gameField;

import ru.vsu.joolsoul.model.card.Card;

import java.util.LinkedList;
import java.util.List;

public class Foundation {

    private List<Card> foundationCards = new LinkedList<>();

    public Foundation() {

    }

    public List<Card> getFoundationCards() {
        return foundationCards;
    }

    public void setFoundationCards(List<Card> foundationCards) {
        this.foundationCards = foundationCards;
    }
}
