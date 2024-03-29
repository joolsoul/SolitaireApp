package ru.vsu.joolsoul.service;

import ru.vsu.joolsoul.model.Game;
import ru.vsu.joolsoul.model.Player;
import ru.vsu.joolsoul.model.StepType;
import ru.vsu.joolsoul.service.cardService.GameDeckService;
import ru.vsu.joolsoul.service.cardService.StacksService;
import ru.vsu.joolsoul.service.stepService.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameService {

    private Map<StepType, IStepService> stepMap = new LinkedHashMap<>();

    public GameService() {
        stepMap.put(StepType.MOVE_TO_FOUNDATION, new MoveToFoundationStepService());
        stepMap.put(StepType.MOVE_TO_STACK, new MoveToStackStepService());
        stepMap.put(StepType.MOVE_KING, new MoveKingStepService());
        stepMap.put(StepType.MOVE_STACKS, new MoveStacksStepService());
        stepMap.put(StepType.OPEN_CARD, new OpenCardStepService());
    }

    public Game createGame(Player player) {
        Game game = new Game(player);
        GameDeckService gameDeckService = new GameDeckService();
        gameDeckService.initDeck(game.getGameDeck());
        StacksService stacksService = new StacksService();
        stacksService.initStacksAndFoundations(game);
        return game;
    }

    public boolean playGame(Game game) {
        for (Map.Entry<StepType, IStepService> entry : stepMap.entrySet()) {
            if (entry.getValue().doStep(game)) {
                return true;
            }
        }
        return false;
    }
}
