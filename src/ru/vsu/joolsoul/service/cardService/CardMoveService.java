package ru.vsu.joolsoul.service.cardService;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.gameField.Foundation;
import ru.vsu.joolsoul.model.gameField.Stack;

import java.util.List;

public class CardMoveService {

    public static void moveFromStackToFoundation(Stack stack, Foundation foundation) {
        Card stackLastCard = stack.getOpenCards().get(stack.getOpenCards().size() - 1);
        foundation.getFoundationCards().add(stackLastCard);
        stack.getOpenCards().remove(stack.getOpenCards().size() - 1);

        if (stack.getOpenCards().isEmpty()) {
            if (!stack.getCloseCards().isEmpty()) {
                Card lastCloseCard = stack.getCloseCards().get(stack.getCloseCards().size() - 1);
                lastCloseCard.showFace();
                stack.getOpenCards().add(lastCloseCard);
                stack.getCloseCards().remove(stack.getCloseCards().size() - 1);
            }
        }
    }

    public static void moveFromOpenCardsToFoundation(List<Card> openCardsList, Foundation foundation) {
        Card openCard = openCardsList.get(openCardsList.size() - 1);
        foundation.getFoundationCards().add(openCard);
        openCardsList.remove(openCardsList.size() - 1);
    }

    public static void moveFromOpenCardsToStack(List<Card> openCardsList, Stack stack) {
        Card openCard = openCardsList.get(openCardsList.size() - 1);
        stack.getOpenCards().add(openCard);
        openCardsList.remove(openCardsList.size() - 1);
    }

    public static void moveFromStackToStack(Stack fromStack, Stack toStack, List<Card> movedCards) {
        toStack.getOpenCards().addAll(movedCards);
        fromStack.getOpenCards().removeAll(movedCards);

        if (fromStack.getOpenCards().isEmpty()) {
            if (!fromStack.getCloseCards().isEmpty()) {
                Card lastCloseCard = fromStack.getCloseCards().get(fromStack.getCloseCards().size() - 1);
                lastCloseCard.showFace();
                fromStack.getOpenCards().add(lastCloseCard);
                fromStack.getCloseCards().remove(fromStack.getCloseCards().size() - 1);
            }
        }
    }

    public static void openCard(Game game) {
        Card cardToOpen = game.getGameDeck().get(0);
        cardToOpen.showFace();
        game.getOpenCards().add(cardToOpen);
        game.getGameDeck().remove(0);
    }
}
