package ru.vsu.samsonova_a_g.model.gameField;

import ru.vsu.samsonova_a_g.model.card.Card;

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
