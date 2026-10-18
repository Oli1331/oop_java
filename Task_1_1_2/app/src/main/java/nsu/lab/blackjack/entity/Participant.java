package nsu.lab.blackjack.entity;

import nsu.lab.blackjack.cards.Card;
import nsu.lab.blackjack.cards.Pile;

import java.util.ArrayList;

/** Abstract participant base class holding hand cards. */
public abstract class Participant {
    private final ArrayList<Card> card = new ArrayList<>();

    /**
     * Calculates the best possible sum of hand points.
     *
     * @return total hand score.
     */
    public int getSumCard() {
        int result = 0;
        int countAce = 0;
        for (Card c : card) {
            int value = c.getValue();
            result += value;
            if (value == 11) {
                countAce++;
            }
        }
        while (result > 21 && countAce > 0) {
            result -= 10;
            countAce--;
        }
        return result;
    }

    /**
     * Adds a drawn card to hand.
     *
     * @param c card instance.
     */
    public void takeCard(Card c) {
        card.add(c);
    }

    /**
     * Returns hand card count.
     *
     * @return total cards held.
     */
    public int getCountCard() {
        return card.size();
    }

    /**
     * Retrieves card at index.
     *
     * @param i zero-based index.
     * @return card at index.
     */
    public Card getCard(int i) {
        return card.get(i);
    }

    /**
     * Checks if current hand forms a natural blackjack.
     *
     * @return true if 2 cards total 21.
     */
    public boolean haveBlackjack() {
        return card.size() == 2 && getSumCard() == 21;
    }

    /** Prints held cards to console. */
    public abstract void printCard();

    /**
     * Discards all held cards into the discard pile.
     *
     * @param discardPile destination pile.
     */
    public void reset(Pile discardPile) {
        for (int i = 0; i < card.size(); i++) {
            discardPile.addCard(card.get(i));
        }
        card.clear();
    }
}
