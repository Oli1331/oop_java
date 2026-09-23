package nsu.lab.blackjack.entity;

import java.util.ArrayList;

import nsu.lab.blackjack.cardsLogic.Card;
import nsu.lab.blackjack.cardsLogic.Pile;

public abstract class Participant {
    private ArrayList<Card> card = new ArrayList<>();

    public int getSumCard() {
        int result = 0;
        int countAce = 0;
        for (Card c : card) {
            int value = c.getValue();
            result += value;
            if (value == 11)
                countAce++;
        }
        while (result > 21 && countAce > 0) {
            result -= 10;
            countAce--;
        }
        return result;
    }

    public void takeCard(Card c) {
        card.add(c);
    }

    public int getCountCard() {
        return card.size();
    }

    public Card getCard(int i) {
        return card.get(i);
    }

    public boolean haveBlackjack() {

        if (card.size() == 2 && getSumCard() == 21) {
            return true;
        }
        return false;
    }

    public abstract void printCard();

    public void reset(Pile discardPile) {
        for (int i = 0; i < card.size(); i++) {
            discardPile.addCard(card.get(i));
        }
        card.clear();
    }
}
