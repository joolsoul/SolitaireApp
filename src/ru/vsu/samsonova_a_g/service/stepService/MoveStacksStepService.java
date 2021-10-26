package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.moveFromStackToStack;

public class MoveStacksStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        for (Map.Entry<StackType, Stack> entry : game.getStacksMap().entrySet()) {
            if (!entry.getValue().getOpenCards().isEmpty()) {
                Stack currentStack = entry.getValue();
                Map<StackType, Stack> copyMap = new LinkedHashMap<>(game.getStacksMap());
                copyMap.remove(entry.getKey());

                for (Map.Entry<StackType, Stack> copyEntry : copyMap.entrySet()) {
                    if (!copyEntry.getValue().getOpenCards().isEmpty()) {
                        Card currentCard = currentStack.getOpenCards().get(0);
                        Card stackCard = copyEntry.getValue().getOpenCards().get(copyEntry.getValue().getOpenCards().size() - 1);
                        if (currentCard.getRank().getValue() - stackCard.getRank().getValue() == -1 && isDifferentColors(currentCard, stackCard)) {
                            List<Card> cardsToMove = new LinkedList<>(currentStack.getOpenCards().subList(0, currentStack.getOpenCards().size()));
                            moveFromStackToStack(currentStack, copyEntry.getValue(), cardsToMove);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
