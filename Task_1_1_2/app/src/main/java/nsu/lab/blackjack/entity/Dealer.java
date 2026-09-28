package nsu.lab.blackjack.entity;
import nsu.lab.blackjack.cards.Card;

/**
 * Represents dealer with hidden hole-card mechanics.
 */
public class Dealer extends Participant {
    private boolean haveSecret = false;

    /**
     * Takes card and hides it as hole card.
     *
     * @param c hidden card.
     */
    public void takeCardAsSecret(Card c) {
        takeCard(c);
        haveSecret = true;
    }

    /**
     * Reveals dealer hole card.
     */
    public void openSecretCard() {
        haveSecret = false;
    }

    /**
     * Returns total sum or -1 if hole card remains hidden.
     *
     * @return points sum or -1.
     */
    @Override
    public int getSumCard() {
        if (haveSecret) {
            return -1;
        }
        return super.getSumCard();
    }

    /**
     * Displays dealer cards concealing secret card if needed.
     */
    @Override
    public void printCard() {
        System.out.print("Карты дилера: {");
        for (int i = 0; i < getCountCard() - 1; i++) {
            System.out.print(getCard(i) + ";");
        }
        if (haveSecret) {
            System.out.print("Секретная карта (?)");
            System.out.println("} == ???");
        } else {
            System.out.print(getCard(getCountCard() - 1));
            System.out.println("} == " + getSumCard());
        }
    }
}