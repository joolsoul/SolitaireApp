package ru.vsu.samsonova_a_g.service.cardService;

import ru.vsu.samsonova_a_g.model.card.Card;
import ru.vsu.samsonova_a_g.model.card.CardRank;
import ru.vsu.samsonova_a_g.model.card.CardSuit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GameDeckService {

    public GameDeckService() {
    }

    public void initDeck(List<Card> gameDeck) {
        try {
            createDeck(gameDeck);
        } catch (Exception ignored) {

        }
        shuffleOfDeck(gameDeck);
    }

    private void createDeck(List<Card> gameDeck) throws IOException {
        int suitCount = 0;
        int x = 0;
        for (CardSuit suit : CardSuit.values()) {
            suitCount++;
            x = 1;
            for (CardRank rank : CardRank.values()) {
                BufferedImage bigImg = ImageIO.read(new File("resources/cards_" + suitCount + ".bmp"));

                BufferedImage image = bigImg.getSubimage(x, 1, 69, 95);

                gameDeck.add(new Card(suit, rank, image));

                x += 71;
            }
        }
    }

    private void shuffleOfDeck(List<Card> gameDeck) {
        Collections.shuffle(gameDeck);
    }
}

