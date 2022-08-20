package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.gameField.Stack;
import ru.vsu.joolsoul.model.gameField.StackType;

import java.util.LinkedHashMap;
import java.util.Map;

import static ru.vsu.joolsoul.service.cardService.CardMoveService.moveFromStackToStack;

public class MoveStacksStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        for (Map.Entry<StackType, Stack> stack : game.getStacksMap().entrySet()) {
            if (!stack.getValue().getOpenCards().isEmpty()) {

                Stack fromStack = stack.getValue();
                Map<StackType, Stack> copyMap = new LinkedHashMap<>(game.getStacksMap());
                copyMap.remove(stack.getKey());

                for (Map.Entry<StackType, Stack> copyStack : copyMap.entrySet()) {
                    if (!copyStack.getValue().getOpenCards().isEmpty()) {

                        Stack toStack = copyStack.getValue();
                        Card fromStackFirstOpenCard = fromStack.getOpenCards().get(0);
                        Card toStackLastOpenCard = toStack.getOpenCards().get(toStack.getOpenCards().size() - 1);

                        if (fromStackFirstOpenCard.getRank().getValue() - toStackLastOpenCard.getRank().getValue() == -1 && isDifferentColors(fromStackFirstOpenCard, toStackLastOpenCard)) {
                            moveFromStackToStack(fromStack, toStack, fromStack.getOpenCards());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
