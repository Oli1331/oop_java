package nsu.lab.blackjack.cardsLogic;

import java.util.concurrent.ThreadLocalRandom;

public class Pile {
    private Card[] card;
    private int countCard;
    private int size;

    public Pile(int countDeck) {
        size = Card.countCard * countDeck;
        card = new Card[size];
        countCard = 0;
    }

    public void fill(int countDeck) {
        for (int i = 0; i < countDeck; i++) {
            for (SuitCard s : SuitCard.values()) {
                for (RankCard r : RankCard.values()) {
                    card[countCard++] = new Card(r, s);
                }
            }
        }
        shuffle();
    }

    public void shuffle() {
        for (int i = 0; i < countCard; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, size);
            int j = (randomNum + i) % countCard;
            Card tmp = card[randomNum];
            card[randomNum] = card[j];
            card[j] = tmp;
        }
    }

    public boolean empty() {
        return countCard == 0;
    }

    public int getCountCard() {
        return countCard;
    }

    public int getSize() {
        return size;
    }

    public void addCard(Card newCard) {
        if (countCard < size)
            card[countCard++] = newCard;
    }

    public Card getCard() {
        if (countCard > 0)
            return card[countCard--];
        return null;
    }

}
