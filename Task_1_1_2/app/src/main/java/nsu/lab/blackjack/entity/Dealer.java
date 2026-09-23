package nsu.lab.blackjack.entity;

import nsu.lab.blackjack.cardsLogic.Card;;

public class Dealer extends Participant {
    private boolean haveSecret = false;

    public void takeCardAsSecret(Card c) {
        takeCard(c);
        haveSecret = true;
    }

    public void openSecretCard() {
        haveSecret = false;
    }

    @Override
    public int getSumCard() {
        if (haveSecret)
            return -1;
        return super.getSumCard();
    }

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
