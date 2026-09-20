package blackjack;

import java.util.ArrayList;

public abstract class Participant {
    protected ArrayList<Card> card = new ArrayList<>();
    protected int sumCard = 0;
    protected boolean haveJQK = false;
    protected int countSaledAce = 0;
    protected int countAce = 0;
    protected int gamePoints = 0;

    public int getGamePoints() {
        return gamePoints;
    }

    public void takeCart(Card c) {
        card.add(c);
        sumCard += c.rank.value;
        if (c.rank.value == 11) {
            countAce++;
        }
        if (c.rank.value == 10)
            haveJQK = true;

        if (sumCard > 21 && countAce > countSaledAce) {
            countSaledAce++;
            sumCard -= 10;
        }

    }

    public boolean haveBlackjack() {
        return countAce > 0 && haveJQK && card.size() == 2;
    }

    public abstract void printCard();

    public void reset(Pile discardPile) {
        for (int i = 0; i < card.size(); i++) {
            discardPile.card[discardPile.countCard++] = card.get(i);
        }
        card.clear();
        sumCard = 0;
        countAce=0;
        haveJQK = false;
        countSaledAce=0;
    }
}
