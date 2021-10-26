package ru.vsu.samsonova_a_g.service.cardService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.card.CardSuit;
import ru.vsu.samsonova_a_g.model.gameField.Foundation;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StacksService {

    public StacksService() {
    }

    public void initStacksAndFoundations(Game game) {
        fillStacks(game.getStacksMap(), game.getGameDeck());
        initFoundations(game.getFoundationsMap());
    }

    private void fillStacks(Map<StackType, Stack> stacksMap, List<Card> gameDeck) {
        int iteration = 1;

        for (int i = 0; i < 7; i++, iteration++) {
            List<Card> closeStackCards = new ArrayList<>();
            Stack currentStack = new Stack();

            for (int j = 0; j < iteration - 1; j++) {
                Card currentCard = gameDeck.get(0);

                closeStackCards.add(currentCard);
                gameDeck.remove(0);

            }

            Card currentCard = gameDeck.get(0);
            currentCard.showFace();
            currentStack.getOpenCards().add(currentCard);
            currentStack.setCloseCards(closeStackCards);
            gameDeck.remove(0);

            stacksMap.put(StackType.values()[i], currentStack);
        }
    }

    private void initFoundations(Map<CardSuit, Foundation> foundationsMap) {
        for (int i = 0; i < 4; i++) {
            foundationsMap.put(CardSuit.values()[i], new Foundation());
        }
    }

    public List<Card> combineCards(Stack stack) {
        List<Card> combineList = new LinkedList<>(stack.getCloseCards());
        combineList.addAll(stack.getOpenCards());

        return combineList;
    }
}
