package ru.vsu.samsonova_a_g.model;

import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.card.CardSuit;
import ru.vsu.samsonova_a_g.model.gameField.Foundation;
import ru.vsu.samsonova_a_g.model.gameField.Stack;
import ru.vsu.samsonova_a_g.model.gameField.StackType;

import java.util.*;

public class Game {

    private List<Card> gameDeck = new ArrayList<>();

    private Map<StackType, Stack> stacksMap = new LinkedHashMap<>();

    private Map<CardSuit, Foundation> foundationsMap = new LinkedHashMap<>();

    private List<Card> openCards = new ArrayList<>();

    private Player player;

    private boolean isInGame = true;


    public Game(Player player) {
        this.player = player;
    }


    public List<Card> getGameDeck() {
        return gameDeck;
    }

    public void setGameDeck(List<Card> gameDeck) {
        this.gameDeck = gameDeck;
    }

    public Map<StackType, ru.vsu.samsonova_a_g.model.gameField.Stack> getStacksMap() {
        return stacksMap;
    }

    public void setStacksMap(Map<StackType, Stack> stacksList) {
        this.stacksMap = stacksList;
    }

    public List<Card> getOpenCards() {
        return openCards;
    }

    public void setOpenCards(List<Card> openCards) {
        this.openCards = openCards;
    }

    public Map<CardSuit, Foundation> getFoundationsMap() {
        return foundationsMap;
    }

    public void setFoundationsMap(Map<CardSuit, Foundation> foundationsMap) {
        this.foundationsMap = foundationsMap;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        isInGame = inGame;
    }
}
