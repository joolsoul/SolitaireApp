package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.Game;
import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.Map;

import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.moveFromOpenCardsToFoundation;
import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.moveFromStackToFoundation;

public class MoveToFoundationStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        if (!game.getOpenCards().isEmpty()) {
            Card card = game.getOpenCards().get(game.getOpenCards().size() - 1);

            if (card.getRank().getValue() == 1) {
                moveFromOpenCardsToFoundation(game.getOpenCards(), game.getFoundationsMap().get(card.getSuit()));
                return true;
            }

            if (!game.getFoundationsMap().get(card.getSuit()).getFoundationCards().isEmpty()) {
                if (game.getFoundationsMap().get(card.getSuit()).getFoundationCards().get(game.getFoundationsMap().get(card.getSuit()).getFoundationCards().size() - 1).getRank().getValue() - card.getRank().getValue() == -1) {
                    moveFromOpenCardsToFoundation(game.getOpenCards(), game.getFoundationsMap().get(card.getSuit()));
                    return true;
                }
            }
        }

        for (Map.Entry<StackType, Stack> entry : game.getStacksMap().entrySet()) {
            if (!entry.getValue().getOpenCards().isEmpty()) {
                Card stackCard = entry.getValue().getOpenCards().get(entry.getValue().getOpenCards().size() - 1);
                if (!game.getFoundationsMap().get(stackCard.getSuit()).getFoundationCards().isEmpty()) {
                    if (game.getFoundationsMap().get(stackCard.getSuit()).getFoundationCards().get(game.getFoundationsMap().get(stackCard.getSuit()).getFoundationCards().size() - 1).getRank().getValue() - stackCard.getRank().getValue() == -1) {
                        moveFromStackToFoundation(entry.getValue(), game.getFoundationsMap().get(stackCard.getSuit()));
                        return true;
                    }
                }
                if (stackCard.getRank().getValue() == 1) {
                    moveFromStackToFoundation(entry.getValue(), game.getFoundationsMap().get(stackCard.getSuit()));
                    return true;
                }
            }
        }
        return false;
    }
}
