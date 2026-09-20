package blackjack;

import java.util.concurrent.ThreadLocalRandom;

public class Pile {
    public Card[] card;
    public int countCard;
    public int countDeck;
    public int size;

    Pile(int countDeck) {
        size = Card.countCard * countDeck;
        this.countDeck = countDeck;
        card = new Card[size];
        countCard = 0;

    }

    public void fill() {
        for (int i = 0; i < countDeck; i++) {
            for (Card.Suit s : Card.Suit.values()) {
                for (Card.Rank r : Card.Rank.values()) {
                    card[countCard++] = new Card(r, s);
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        for (int i = 0; i < countCard; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 52);
            int j = (randomNum + i) % countCard;
            Card tmp = card[randomNum];
            card[randomNum] = card[j];
            card[j] = tmp;
        }
    }

    public boolean empty() {
        return countCard == 0;
    }


}
