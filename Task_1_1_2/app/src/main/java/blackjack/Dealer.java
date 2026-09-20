package blackjack;

public class Dealer extends Participant {
    private boolean haveSecret = false;

    public void takeCartAsSecret(Card c) {
        card.add(c);
        haveSecret = true;
    }

    public void openSecretCard() {
        Card c = card.getLast();
        haveSecret = false;
        sumCard += c.rank.value;
        if (c.rank.value == 11)
            countAce++;

        if (c.rank.value == 10)
            haveJQK = true;

        if (sumCard > 21 && countAce > countSaledAce) {
            countSaledAce++;
            sumCard -= 10;
        }
    }

    @Override
    public void printCard() {
        System.out.print("Карты дилера: {");
        for (int i = 0; i < card.size() - 1; i++) {
            System.out.print(card.get(i) + ";");
        }
        if (haveSecret) {

            System.out.print("Секретная карта (?)");
            System.out.println("} == ???");
        } else {

            System.out.print(card.getLast());
            System.out.println("} == " + sumCard);
        }

    }

}
