package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.Game;

public interface IStepService {

    boolean doStep(Game game);

    default boolean isDifferentColors(Card firstCard, Card secondCard) {
        return firstCard.getSuit().getColorType() != secondCard.getSuit().getColorType();
    }
}
