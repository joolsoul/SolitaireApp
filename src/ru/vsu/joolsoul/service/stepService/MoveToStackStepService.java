package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.gameField.Stack;
import ru.vsu.joolsoul.model.gameField.StackType;

import java.util.Map;

import static ru.vsu.joolsoul.service.cardService.CardMoveService.moveFromOpenCardsToStack;

public class MoveToStackStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        if (!game.getOpenCards().isEmpty()) {

            Card lastOpenCard = game.getOpenCards().get(game.getOpenCards().size() - 1);

            for (Map.Entry<StackType, Stack> currentStack : game.getStacksMap().entrySet()) {
                if (!currentStack.getValue().getOpenCards().isEmpty()) {

                    Card lastStackCard = currentStack.getValue().getOpenCards().get(currentStack.getValue().getOpenCards().size() - 1);

                    if (lastOpenCard.getRank().getValue() - lastStackCard.getRank().getValue() == -1 && isDifferentColors(lastStackCard, lastOpenCard)) {
                        moveFromOpenCardsToStack(game.getOpenCards(), currentStack.getValue());
                        return true;
                    }
                }
                else if (lastOpenCard.getRank().getValue() == 13 && currentStack.getValue().getOpenCards().isEmpty()) {
                    moveFromOpenCardsToStack(game.getOpenCards(), currentStack.getValue());
                    return true;
                }
            }
        }
        return false;
    }
}
