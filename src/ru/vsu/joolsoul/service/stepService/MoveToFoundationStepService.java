package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.card.Card;
import ru.vsu.joolsoul.model.gameField.Foundation;
import ru.vsu.joolsoul.model.gameField.Stack;
import ru.vsu.joolsoul.model.gameField.StackType;

import java.util.Map;

import static ru.vsu.joolsoul.service.cardService.CardMoveService.moveFromOpenCardsToFoundation;
import static ru.vsu.joolsoul.service.cardService.CardMoveService.moveFromStackToFoundation;

public class MoveToFoundationStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        if (!game.getOpenCards().isEmpty()) {

            Card lastOpenCard = game.getOpenCards().get(game.getOpenCards().size() - 1);
            Foundation currentOpenCardFoundation = game.getFoundationsMap().get(lastOpenCard.getSuit());

            if (lastOpenCard.getRank().getValue() == 1) {
                moveFromOpenCardsToFoundation(game.getOpenCards(), currentOpenCardFoundation);
                return true;
            }

            if (!currentOpenCardFoundation.getFoundationCards().isEmpty()) {
                if (currentOpenCardFoundation.getFoundationCards().get(currentOpenCardFoundation.getFoundationCards().size() - 1).getRank().getValue() - lastOpenCard.getRank().getValue() == -1) {
                    moveFromOpenCardsToFoundation(game.getOpenCards(), currentOpenCardFoundation);
                    return true;
                }
            }
        }

        for (Map.Entry<StackType, Stack> currentStack : game.getStacksMap().entrySet()) {
            if (!currentStack.getValue().getOpenCards().isEmpty()) {

                Card lastOpenStackCard = currentStack.getValue().getOpenCards().get(currentStack.getValue().getOpenCards().size() - 1);
                Foundation currentStackCardFoundation = game.getFoundationsMap().get(lastOpenStackCard.getSuit());

                if (!currentStackCardFoundation.getFoundationCards().isEmpty()) {
                    if (currentStackCardFoundation.getFoundationCards().get(currentStackCardFoundation.getFoundationCards().size() - 1).getRank().getValue() - lastOpenStackCard.getRank().getValue() == -1) {
                        moveFromStackToFoundation(currentStack.getValue(), currentStackCardFoundation);
                        return true;
                    }
                }
                if (lastOpenStackCard.getRank().getValue() == 1) {
                    moveFromStackToFoundation(currentStack.getValue(), currentStackCardFoundation);
                    return true;
                }
            }
        }
        return false;
    }
}
