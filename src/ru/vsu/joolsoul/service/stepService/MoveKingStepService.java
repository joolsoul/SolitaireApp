package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.gameField.Stack;
import ru.vsu.joolsoul.model.gameField.StackType;

import java.util.LinkedHashMap;
import java.util.Map;

import static ru.vsu.joolsoul.service.cardService.CardMoveService.moveFromStackToStack;

public class MoveKingStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        for (Map.Entry<StackType, Stack> currentStack : game.getStacksMap().entrySet()) {
            if (currentStack.getValue().getOpenCards().isEmpty()) {

                Stack emptyToStack = currentStack.getValue();
                Map<StackType, Stack> copyMap = new LinkedHashMap<>(game.getStacksMap());
                copyMap.remove(currentStack.getKey());

                for (Map.Entry<StackType, Stack> copyStack : copyMap.entrySet()) {
                    if (!copyStack.getValue().getOpenCards().isEmpty()) {

                        Stack fromStack = copyStack.getValue();
                        Card FirstOpenStackCard = fromStack.getOpenCards().get(0);

                        if (FirstOpenStackCard.getRank().getValue() == 13 && !fromStack.getCloseCards().isEmpty()) {
                            moveFromStackToStack(fromStack, emptyToStack, fromStack.getOpenCards());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
