package ru.vsu.samsonova_a_g.service.stepService;

import ru.vsu.samsonova_a_g.model.Game;

import static ru.vsu.samsonova_a_g.service.cardService.CardMoveService.openCard;

public class OpenCardStepService implements IStepService {

    @Override
    public boolean doStep(Game game) {
        if (!game.getGameDeck().isEmpty()) {
            openCard(game);
            return true;
        }
        return false;
    }
}
