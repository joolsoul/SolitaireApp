package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.Map;

import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.moveFromOpenCardsToStack;

public class MoveToStackStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        if (!game.getOpenCards().isEmpty()) {
            Card card = game.getOpenCards().get(game.getOpenCards().size() - 1);

            for (Map.Entry<StackType, Stack> entry : game.getStacksMap().entrySet()) {
                if (!entry.getValue().getOpenCards().isEmpty()) {
                    Card stackCard = entry.getValue().getOpenCards().get(entry.getValue().getOpenCards().size() - 1);
                    if (stackCard.getRank().getValue() - card.getRank().getValue() == 1 && isDifferentColors(stackCard, card)) {
                        moveFromOpenCardsToStack(game.getOpenCards(), entry.getValue());
                        return true;
                    }
                } else if (card.getRank().getValue() == 13 && entry.getValue().getOpenCards().isEmpty()) {
                    moveFromOpenCardsToStack(game.getOpenCards(), entry.getValue());
                    return true;
                }
            }
        }
        return false;
    }
}
