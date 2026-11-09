package nsu.lab.blackjack.cards;

import java.util.concurrent.ThreadLocalRandom;

/** Represents a card deck or discard pile. */
public class Pile {
    private final Card[] card;
    private final int size;
    private int countCard;

    /**
     * Allocates pile memory for the given deck amount.
     *
     * @param countDeck number of full standard decks.
     */
    public Pile(int countDeck) {
        size = Card.countCard * countDeck;
        card = new Card[size];
        countCard = 0;
    }

    /**
     * Fills the pile with ordered cards and shuffles them.
     *
     * @param countDeck number of decks to add.
     */
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

    /** Shuffles remaining cards in the pile. */
    public void shuffle() {
        for (int i = 0; i < countCard; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, size);
            int j = (randomNum + i) % countCard;
            Card tmp = card[randomNum];
            card[randomNum] = card[j];
            card[j] = tmp;
        }
    }

    /**
     * Checks if the pile is empty.
     *
     * @return true if no cards left.
     */
    public boolean empty() {
        return countCard == 0;
    }

    /**
     * Returns the current amount of cards in pile.
     *
     * @return card count.
     */
    public int getCountCard() {
        return countCard;
    }

    /**
     * Returns total pile capacity.
     *
     * @return max size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a card to the top of the pile.
     *
     * @param newCard card to insert.
     */
    public void addCard(Card newCard) {
        if (countCard < size) {
            card[countCard++] = newCard;
        }
    }

    /**
     * Pops and returns the top card from the pile.
     *
     * @return top card or null if empty.
     */
    public Card getCard() {
        if (countCard > 0) {
            return card[--countCard];
        }
        return null;
    }
}
