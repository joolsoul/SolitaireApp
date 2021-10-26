package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.Game;

public interface IStepService {

    boolean doStep(Game game);

    default boolean isDifferentColors(Card firstCard, Card secondCard) {
        return firstCard.getSuit().getColorType() != secondCard.getSuit().getColorType();
    }
}
