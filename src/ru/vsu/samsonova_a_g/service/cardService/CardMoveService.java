package ru.vsu.samsonova_a_g.service.cardService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.gameField.Foundation;
import ru.vsu.samsonova_a_g.model.gameField.Stack;

import java.util.List;

public class CardMoveService {

    public static void moveFromStackToFoundation(Stack stack, Foundation foundation) {
        foundation.getFoundationCards().add(stack.getOpenCards().get(stack.getOpenCards().size() - 1));
        stack.getOpenCards().remove(stack.getOpenCards().size() - 1);

        if (stack.getOpenCards().isEmpty()) {
            if (!stack.getCloseCards().isEmpty()) {
                Card currentCard = stack.getCloseCards().get(stack.getCloseCards().size() - 1);
                currentCard.showFace();
                stack.getOpenCards().add(currentCard);
                stack.getCloseCards().remove(stack.getCloseCards().size() - 1);
            }
        }
    }

    public static void moveFromOpenCardsToFoundation(List<Card> openCards, Foundation foundation) {
        foundation.getFoundationCards().add(openCards.get(openCards.size() - 1));
        openCards.remove(openCards.size() - 1);
    }

    public static void moveFromOpenCardsToStack(List<Card> openCards, Stack stack) {
        stack.getOpenCards().add(openCards.get(openCards.size() - 1));
        openCards.remove(openCards.size() - 1);
    }

    public static void moveFromStackToStack(Stack firstStack, Stack secondStack, List<Card> movedCards) {
        secondStack.getOpenCards().addAll(movedCards);
        firstStack.getOpenCards().removeAll(movedCards);

        if (firstStack.getOpenCards().isEmpty()) {
            if (!firstStack.getCloseCards().isEmpty()) {
                firstStack.getCloseCards().get(firstStack.getCloseCards().size() - 1).showFace();

                firstStack.getOpenCards().add(firstStack.getCloseCards().get(firstStack.getCloseCards().size() - 1));
                firstStack.getCloseCards().remove(firstStack.getCloseCards().size() - 1);
            }
        }
    }

    public static void openCard(Game game) {
        game.getOpenCards().add(game.getGameDeck().get(0));
        game.getGameDeck().get(0).showFace();
        game.getGameDeck().remove(0);
    }
}
