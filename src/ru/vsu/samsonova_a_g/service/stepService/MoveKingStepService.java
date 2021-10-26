package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.LinkedHashMap;
import java.util.Map;

import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.moveFromStackToStack;

public class MoveKingStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        for (Map.Entry<StackType, Stack> entry : game.getStacksMap().entrySet()) {
            if (entry.getValue().getOpenCards().isEmpty()) {
                Stack emptyStack = entry.getValue();
                Map<StackType, Stack> copyMap = new LinkedHashMap<>(game.getStacksMap());
                copyMap.remove(entry.getKey());

                for (Map.Entry<StackType, Stack> copyEntry : copyMap.entrySet()) {
                    if (!copyEntry.getValue().getOpenCards().isEmpty()) {
                        Card card = copyEntry.getValue().getOpenCards().get(0);

                        if (card.getRank().getValue() == 13 && !copyEntry.getValue().getCloseCards().isEmpty()) {
                            moveFromStackToStack(copyEntry.getValue(), emptyStack, copyEntry.getValue().getOpenCards());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
