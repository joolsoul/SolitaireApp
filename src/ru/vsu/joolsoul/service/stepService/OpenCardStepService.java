package ru.vsu.joolsoul.service.stepService;

import ru.vsu.joolsoul.model.Game;

import static ru.vsu.joolsoul.service.cardService.CardMoveService.openCard;

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
